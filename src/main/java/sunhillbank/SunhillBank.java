package sunhillbank;

import java.math.BigDecimal;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import sun.util.logging.resources.logging;
import sunhillbank.accounts.CheckingAccount;
import sunhillbank.accounts.SavingAccount;

import static sunhillbank.BankUtils.areBigDecimalsSame;

//Singleton Bank, here the accounts can be registered later

@Slf4j
@Service
public class SunhillBank {

    public void transferBetweenCheckingAccounts(CheckingAccount fromAccount, CheckingAccount toAccount, BigDecimal amount){
    	try {
        	transactionalTransferBetweenCheckingAccounts(fromAccount, toAccount, amount);
    	}
    	catch (Exception e) {
    		log.error("Error While trying to Transfer Between Checking Accounts, transfer has been reverted", e);
		}

    }


	private void transactionalTransferBetweenCheckingAccounts(CheckingAccount fromAccount, CheckingAccount toAccount, BigDecimal amount) throws Exception {
		BigDecimal fromAccountInitialBalance = fromAccount.getBalance();

		log.debug("New transaction between checkingAccounts " + fromAccount.toString() + " and " + toAccount.toString());
		try {
			fromAccount.withdraw(amount);
			toAccount.deposit(amount);

		} catch (Exception e) {
			if (!areBigDecimalsSame(fromAccount.getBalance(), fromAccountInitialBalance)) {
				fromAccount.deposit(amount);
			}
			throw e;
		}
	}



}