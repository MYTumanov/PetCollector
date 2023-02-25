package ru.petcollector.petcollector.repository;

import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.mongodb.repository.Aggregation;

import ru.petcollector.petcollector.model.debt.Debt;

public interface DebtRepository extends AbstractRepository<Debt> {

        // Find all debts where userId is owner or participant.
        // Sorted by lastUpdate desc.
        @Nullable
        @Aggregation(pipeline = { "{$match:{$and[{'debtors.userId':ObjectId('?0')},{'isDeleted':false}]}}",
                        "{$addFields: { debtors: { $filter: { input: '$debtors', as: 'debtor', cond: { $eq: ['$$debtor.userId', ObjectId('?0')] } } } } }",
                        "{$unionWith: {coll: 'debts', pipeline: [{$match:{$and:[{'ownerId': ObjectId('?0')},{'isDeleted':false}]}}]}}",
                        "{$sort : { 'lastUpdate' : -1 } }" })
        public List<Debt> findAllByDebtorUserId(@NotNull final String userId);

        // Find debt by id where userId owner or participant.
        @NotNull
        @Aggregation(pipeline = {
                        "{$match:{$and:[{'_id':ObjectId('?0')},{'debtors.userId':ObjectId('?1')},{'isDeleted':false}]}}",
                        "{$addFields: { debtors: { $filter: { input: '$debtors', as: 'debtor', cond: { $eq: ['$$debtor.userId', ObjectId('?1')]}}}}}",
                        "{$unionWith: { coll: 'debts', pipeline: [{ $match: {$and:[{{'_id':ObjectId('?0')}},{'ownerId': ObjectId('?1')}, {'isDeleted':false}]}}]}}" })
        public Optional<Debt> findByIdAndUserId(@NotNull final String id, @NotNull final String userId);

        // Find all debts where userId is owner or participants
        // and statuses are equal to given.
        // Sorted by lastUpdate desc.
        @Nullable
        @Aggregation(pipeline = {
                        "{$match:{$and:[{'debtors.userId':ObjectId('?0')}, {'status':{$in:[?1]}}, {'isDeleted':false}]}}",
                        "{$addFields: { debtors: { $filter: { input: '$debtors', as: 'debtor', cond: { $eq: ['$$debtor.userId', ObjectId('?0')] } } } } }",
                        "{$unionWith: { coll: 'debts', pipeline: [{ $match: {$and:[{'ownerId': ObjectId('?0')}, {'status':{$in:[?1]}}, {'isDeleted':false}]}}]}}",
                        "{$sort : { 'lastUpdate' : -1 } }" })
        public List<Debt> findAllByUserIdAndStatuses(@NotNull final String userId, @NotNull final String... statuses);
}
