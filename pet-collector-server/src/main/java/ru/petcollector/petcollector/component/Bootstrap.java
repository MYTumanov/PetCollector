package ru.petcollector.petcollector.component;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import ru.petcollector.petcollector.model.Debt;
import ru.petcollector.petcollector.model.User;
import ru.petcollector.petcollector.repository.DebtRepository;
import ru.petcollector.petcollector.repository.UserRepository;

@Component
public class Bootstrap {

    @NotNull
    private UserRepository userRepository;

    @NotNull
    private DebtRepository debtRepository;

    public Bootstrap(ConfigurableApplicationContext applicationContext) {
        this.userRepository = applicationContext.getBean(UserRepository.class);
        this.debtRepository = applicationContext.getBean(DebtRepository.class);
    }

    private void initData() {
        @NotNull
        final User user1 = new User();
        user1.setFirsName("Админ");
        user1.setLastName("Админов");
        user1.setMidleName("Админович");
        user1.setLogin("admin");

        @NotNull
        final User user2 = new User();
        user2.setFirsName("ПростоЮзер");
        user2.setLastName("Просто");
        user2.setMidleName("Юзерович");
        user2.setLogin("prosto");

        @NotNull
        final User user3 = new User();
        user3.setFirsName("Test");
        user3.setLastName("Test");
        user3.setMidleName("Test");
        user3.setLogin("test");

        @NotNull
        final Debt debt1 = new Debt();
        debt1.setOwnerId(user3.getId());
        debt1.setDebtorId(user2.getId());
        debt1.setSum(421f);

        @NotNull
        final Debt debt2 = new Debt();
        debt2.setOwnerId(user3.getId());
        debt2.setDebtorId(user1.getId());
        debt2.setSum(12.3f);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        debtRepository.save(debt1);
        debtRepository.save(debt2);
    }

    public void run() {
        try {
            System.out.println("***Initialise data***");
            initData();
            System.out.println("***Initialise success***");
        } catch (@NotNull final Exception e) {
            System.out.println("***ERROR***");
            System.out.println(e);
        }
    }

}
