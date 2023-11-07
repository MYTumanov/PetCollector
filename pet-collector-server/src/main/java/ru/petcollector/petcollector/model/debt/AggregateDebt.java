package ru.petcollector.petcollector.model.debt;

import java.util.Date;

import org.jetbrains.annotations.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityResult;
import jakarta.persistence.FieldResult;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.SqlResultSetMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedNativeQuery(name = "AggregateDebt", query = "SELECT total_debt, debt_user_id, last_created, tg.user_name tg_name, \r\n" +
        "wb.last_name, wb.first_name, wb.mid_name, wb.login, wb.phone_number\r\n" +
        "FROM (SELECT SUM(CASE WHEN d.owner_id = :userId THEN dr.sum ELSE -1 * dr.sum END) as total_debt,\r\n" +
        "CASE WHEN d.owner_id = :userId THEN u.id ELSE d.owner_id END as debt_user_id, \r\n" +
        "MAX(dr.created) last_created\r\n" +
        "FROM debt d JOIN debtor dr ON d.id = dr.debt_id\r\n" +
        "JOIN users u ON u.id =dr.user_id\r\n" +
        "WHERE u.id != d.owner_id AND (u.id = :userId OR d.owner_id = :userId)\r\n" +
        "GROUP BY debt_user_id) v_t LEFT JOIN users_tg tg ON v_t.debt_user_id = tg.par_id\r\n" +
        "LEFT JOIN users_web wb ON v_t.debt_user_id = wb.par_id", resultSetMapping = "AggregateDebtResult")
@SqlResultSetMapping(name = "AggregateDebtResult", entities = {
        @EntityResult(entityClass = AggregateDebt.class, fields = {
                @FieldResult(name = "totalDebt", column = "total_debt"),
                @FieldResult(name = "debtUserId", column = "debt_user_id"),
                @FieldResult(name = "lastCreated", column = "last_created"),
                @FieldResult(name = "telegramName", column = "tg_name"),
                @FieldResult(name = "lastName", column = "last_name"),
                @FieldResult(name = "firstName", column = "first_name"),
                @FieldResult(name = "midName", column = "mid_name"),
                @FieldResult(name = "login", column = "login"),
                @FieldResult(name = "phoneNumber", column = "phone_number") }) })
public class AggregateDebt {

    @NotNull
    private Double totalDebt;

    @Id
    private String debtUserId;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastCreated;

    private String telegramName;

    private String lastName;

    private String firstName;

    private String midName;

    private String login;

    private String phoneNumber;

}
