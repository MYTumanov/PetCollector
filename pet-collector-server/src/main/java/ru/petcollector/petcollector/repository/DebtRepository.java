package ru.petcollector.petcollector.repository;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.mongodb.repository.Aggregation;

import ru.petcollector.petcollector.enumerated.DebtStatus;
import ru.petcollector.petcollector.model.debt.AggregateDebt;
import ru.petcollector.petcollector.model.debt.Debt;
import ru.petcollector.petcollector.model.debt.DebtDetail;

public interface DebtRepository extends AbstractRepository<Debt> {

    // Find all debts where userId is owner or participant.
    // Sorted by created desc.
    @Nullable
    @Aggregation(pipeline = {
            "{$match: {$or: [{$and: [{'debtors.userId':ObjectId('?0')}, {'isDeleted':false}]},{$and: [{'ownerId':ObjectId('?0')}, {'isDeleted':false}]}]}},",
            """
            {$facet: {
                    'userOwe': [
                        {$match: {'debtors.userId':ObjectId('?0')}},
                        {$addFields: {
                            debtors: {$filter: {
                                input: '$debtors',
                                as: 'debtor',
                                cond: {$eq: ['$$debtor.userId', ObjectId('?0')]}
                                }}
                            }
                        },
                        {$unwind: '$debtors'},
                        {$group: {
                            _id: '$ownerId',
                            totalDebt: {$sum: {$multiply: ['$debtors.sum', -1]}},
                            lastCreated: {$max: '$created'}
                            }
                        }
                    ],
                    'oweToUser': [
                        {$match: {'ownerId':ObjectId('?0')}},
                        {$unwind: '$debtors'},
                        {$group: {
                            _id: '$debtors.userId',
                            totalDebt: {$sum: '$debtors.sum'},
                            lastCreated: {$max: '$created'}
                            }
                        }
                    ]
            }}""",
            "{$project: {data: {$concatArrays: ['$userOwe', '$oweToUser']}}}",
            "{$unwind: '$data'}",
            "{$replaceRoot: {newRoot: '$data'}}",
            """
            {$group: {
                    _id: {$toObjectId: '$_id'},
                    totalDebt: {$sum: '$totalDebt'},
                    lastCreated: {$max: '$lastCreated'}
            }}""",
            "{$sort: {lastCreated: -1}}"
    })
    public List<AggregateDebt> findAllByDebtorUserId(@NotNull final String userId);

    // Find debt by id where userId owner or participant.
    @NotNull
    @Aggregation(pipeline = {
            "{$match:{$and:[{'_id':ObjectId('?0')},{'debtors.userId':ObjectId('?1')},{'isDeleted':false}]}}",
            "{$addFields: { debtors: { $filter: { input: '$debtors', as: 'debtor', cond: { $eq: ['$$debtor.userId', ObjectId('?1')]}}}}}",
            "{$unionWith: { coll: 'debts', pipeline: [{ $match: {$and:[{'_id':ObjectId('?0')},{'ownerId': ObjectId('?1')}, {'isDeleted':false}]}}]}}" })
    public Optional<Debt> findByIdAndUserId(@NotNull final String id, @NotNull final String userId);

    @NotNull
    @Aggregation(pipeline = {
            "{$match:{$or : [{$and:[{'ownerId':ObjectId('?1')},{'debtors.userId':ObjectId('?0')},{'isDeleted':false}]}, {$and:[{'ownerId':ObjectId('?0')},{'debtors.userId':ObjectId('?1')},{'isDeleted':false}]}]}}",
            "{$addFields: { debtors: { $filter: { input: '$debtors', as: 'debtor', cond: { $or : [{$eq: ['$$debtor.userId', ObjectId('?0')]},{$eq: ['$$debtor.userId', ObjectId('?1')]}]}}}}}",
            "{$project : {ownerId : 1, sum : '$debtors.sum', status : '$debtors.status', created : 1, debtorId : '$debtors.userId'}}",
            "{$unwind: '$sum'}",
            "{$unwind: '$status'}",
            "{$unwind: '$debtorId'}",
            "{$sort : {'created' : -1}}"})
    public Optional<List<DebtDetail>> findByDebtorIdAndUserId(@NotNull final String debtorId, @NotNull final String userId);

    // Find all debts where userId is owner or participants
    // and statuses are equal to given.
    // Sorted by created desc.
    @Nullable
    @Aggregation(pipeline = {
            "{$match:{$and:[{'debtors.userId':ObjectId('?0')}, {'status':{$in:?1}}, {'isDeleted':false}]}}",
            "{$addFields: { debtors: { $filter: { input: '$debtors', as: 'debtor', cond: { $eq: ['$$debtor.userId', ObjectId('?0')] } } } } }",
            "{$unionWith: { coll: 'debts', pipeline: [{ $match: {$and:[{'ownerId': ObjectId('?0')}, {'status':{$in:?1}}, {'isDeleted':false}]}}]}}",
            "{$sort : { 'created' : -1 } }" })
    public List<Debt> findAllByUserIdAndStatuses(@NotNull final String userId,
            @NotNull final DebtStatus... statuses);
}
