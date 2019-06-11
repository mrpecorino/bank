package sunhillbank.accounts;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sunhillbank.Owner;

import java.math.BigDecimal;

@Slf4j
public class SavingAccountTest {
    private Owner owner;

    @Test
    public void testdeposit(){
        double interestrate = 0.05;
        SavingAccount savingAccount = null;
        BigDecimal depositAmount = new BigDecimal(1000);
        BigDecimal interestrateBD = BigDecimal.valueOf(interestrate);
        BigDecimal targetAmount = depositAmount.multiply(interestrateBD).add(depositAmount);
        try {
            savingAccount = new SavingAccount(owner, interestrate);
            savingAccount.deposit(depositAmount);
            Assert.assertTrue(savingAccount.getBalance().compareTo(depositAmount)== 0);
            savingAccount.payInterest();
        }
        catch (Exception e){
            log.error("Test failed with exception ",e);
            Assert.fail();
        }
        Assert.assertTrue(savingAccount.getBalance().compareTo(targetAmount) == 0);
    }

    @Before
    public void setup(){
        this.owner = Owner.builder()
                .address("Prag")
                .firstName("Thomas")
                .lastName("Obermeier")
                .build();
    }
}
