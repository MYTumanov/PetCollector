package ru.petcollector.petcollector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ru.petcollector.petcollector.enumerated.DebtStatus;
import ru.petcollector.petcollector.model.debt.Debt;
import ru.petcollector.petcollector.model.debtor.Debtor;
import ru.petcollector.petcollector.repository.DebtRepository;

@SpringBootApplication
public class PetCollectorApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(PetCollectorApplication.class, args);
		init(ctx);
	}

	private static void init(final ConfigurableApplicationContext ctx) {
		DebtRepository repository = ctx.getBean(DebtRepository.class);

		Debtor debtor1 = new Debtor();
		debtor1.setUserId("63b06af839820206d1e8c69b");
		debtor1.setSum(75f);
		debtor1.setPhoneNumber("+79254719928");
		debtor1.setStatus(DebtStatus.IN_PROCCESS);

		Debtor debtor2 = new Debtor();
		// debtor2.setUserId("63b84530969e456d003b84e1");
		// debtor2.setSum(25f);
		debtor2.setPhoneNumber("+79254719930");
		debtor2.setStatus(DebtStatus.IN_PROCCESS);

		Debt debt = new Debt();
		debt.setTotalSum(150f);
		debt.setOwnerId("63b845a0969e456d003b84e2");
		debt.getDebtors().add(debtor1);
		debt.getDebtors().add(debtor2);

		// repository.save(debt);

		// System.out.println(repository.findAllByDebtorUserId("63b84530969e456d003b84e1"));
	}

}
