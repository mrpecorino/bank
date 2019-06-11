package sunhillbank.accounts;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import sunhillbank.Owner;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Getter
public abstract class Account {
	protected final Owner owner;

	protected BigDecimal balance = new BigDecimal(0);

	public Account(@NonNull Owner owner){
		this.owner=owner;
	}
	
	public void deposit(@NonNull BigDecimal amount) throws Exception{
		try {
			validateAmountIsGreaterThanZero(amount);
			this.balance = balance.add(amount);
		}
		catch (Exception e){
			log.error("Error when depositing money");
			throw e;
		}

	}
	
	public void withdraw(@NonNull BigDecimal amount) throws Exception{
		try {
			validateAmountIsGreaterThanZero(amount);
			this.balance = balance.subtract(amount);
		}
		catch (Exception e){
			log.error("Error when depositing money");
			throw e;
		}
	}

	private void validateAmountIsGreaterThanZero(BigDecimal amount) throws Exception{
		if (!(amount.compareTo(new BigDecimal(0))> 0)){
			throw new Exception("The transaction amount is lower than Zero"); 
		}
	}
}
