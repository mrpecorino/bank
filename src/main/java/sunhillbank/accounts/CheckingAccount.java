package sunhillbank.accounts;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import sunhillbank.Owner;

import java.math.BigDecimal;

@Slf4j
public class CheckingAccount extends Account {

    private final BigDecimal overdraftLimit;

    public CheckingAccount(@NonNull BigDecimal overdraftLimit, @NonNull Owner owner) throws Exception{
        super(owner);
        this.overdraftLimit = overdraftLimit;
    }

    public void withdraw(@NonNull BigDecimal amount) throws Exception{
        if (balance.subtract(amount).compareTo(overdraftLimit.negate())<0){
            throw new Exception("Overdraftlimit reached");
        }
		super.withdraw(amount);
	}




}
