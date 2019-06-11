package sunhillbank;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sunhillbank.accounts.CheckingAccount;

import static org.mockito.ArgumentMatchers.any;
import static sunhillbank.BankUtils.*;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SunhillBankTest {

    @Autowired
    private SunhillBank sunhillBank;

    private Owner owner;

    @Test
    public void testTransaction(){
        BigDecimal transferAmount = new BigDecimal(1000);
        BigDecimal overdraftLimit = new BigDecimal(500);

        CheckingAccount fromAccount = null;
        CheckingAccount toAccount = null;
        try {
            fromAccount = new CheckingAccount(overdraftLimit, owner);
            toAccount = new CheckingAccount(overdraftLimit, owner);
            fromAccount.deposit(transferAmount);

            this.sunhillBank.transferBetweenCheckingAccounts(fromAccount, toAccount, transferAmount);
        }
        catch (Exception e){
            Assert.fail();
        }

        Assert.assertTrue(fromAccount.getBalance().equals(new BigDecimal(0)));
        Assert.assertTrue(areBigDecimalsSame(toAccount.getBalance(), transferAmount));
    }

    @Test
    public void testTransferBetweenCheckingAccountsnegative(){
        BigDecimal transferAmount = new BigDecimal(1000);
        BigDecimal overdraftLimit = new BigDecimal(500);
        CheckingAccount toAccount = Mockito.mock(CheckingAccount.class);
        CheckingAccount fromAccount = null;

        try {
            fromAccount = new CheckingAccount(overdraftLimit, owner);
            fromAccount.deposit(transferAmount);
            Mockito.doThrow(new Exception()).when(toAccount).deposit(any(BigDecimal.class));

            this.sunhillBank.transferBetweenCheckingAccounts(fromAccount, toAccount, transferAmount);
        }
        catch (Exception e){

        }

        Assert.assertTrue(areBigDecimalsSame(fromAccount.getBalance(), transferAmount));

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
