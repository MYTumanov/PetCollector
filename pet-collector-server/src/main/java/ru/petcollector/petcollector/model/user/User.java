package ru.petcollector.petcollector.model.user;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.petcollector.petcollector.model.AbstractModel;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@NamedEntityGraph(name = "user-extuser-graph", attributeNodes = {
        @NamedAttributeNode("telegramUser"),
        @NamedAttributeNode("webUser") })
public class User extends AbstractModel {

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private TelegramUser telegramUser;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private WebUser webUser;

    public void setTelegramUser(final TelegramUser telegramUser) {
        this.telegramUser = telegramUser;
        telegramUser.setUser(this);
    }

    public void setTelegramUser(final WebUser webUser) {
        this.webUser = webUser;
        webUser.setUser(this);
    }

}
