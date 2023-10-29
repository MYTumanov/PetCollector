package ru.petcollector.petcollector.model.user;

import org.jetbrains.annotations.Nullable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users_tg")
public class TelegramUser {

    @Nullable
    @Column(name = "telegram_id")
    private Long userTelegramId;

    @Nullable
    private Long chatId;

    @Nullable
    @Column(name = "user_name")
    private String telegramUserName;

    @Id
    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "par_id", referencedColumnName = "id")
    private User user;

}
