package sunhillbank.accounts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sunhillbank.Owner;
import static sunhillbank.BankUtils.*;

import java.math.BigDecimal;

public class CheckingAccountTest {
    private Owner owner;

    @Test
    public void depositAndWithdrawFromCheckingAccountTestShouldNotFail(){
        CheckingAccount checkingAccount = null;
        BigDecimal overdraftLimit = new BigDecimal(1000);
        BigDecimal depositAmount = new BigDecimal(2000);
        BigDecimal withdrawalAmount = new BigDecimal(1000);
        BigDecimal targetAmount = new BigDecimal(1000);
        try{
            checkingAccount = new CheckingAccount(overdraftLimit, owner);
            checkingAccount.deposit(depositAmount);
            Assert.assertTrue(checkingAccount.getBalance().compareTo(depositAmount) == 0);

            checkingAccount.withdraw(withdrawalAmount);
        }
        catch (Exception e){
            Assert.fail();
        }

        Assert.assertTrue(checkingAccount.getBalance().compareTo(targetAmount)==0);
    }

    @Test
    public void withdrawMoreThanLimitShouldFailTest(){
        BigDecimal overdraftLimit = new BigDecimal(1000);
        BigDecimal withdrawalAmount = new BigDecimal(1005);
        BigDecimal targetAmount = new BigDecimal(0);
        CheckingAccount checkingAccount = null;
        Exception exception = null;
        try{
            checkingAccount = new CheckingAccount(overdraftLimit, owner);
            checkingAccount.withdraw(withdrawalAmount);
        }
        catch (Exception e){
            exception = e;
        }
        Assert.assertTrue(exception != null);
        Assert.assertTrue(checkingAccount.getBalance().compareTo(targetAmount) == 0);
    }

//    @Test
//    public void transferToAnotherAccountTest(){
//        CheckingAccount toCheckingAccount = null;
//        CheckingAccount fromCheckingAccount = null;
//        BigDecimal depositAmount = new BigDecimal(2000);
//        BigDecimal overdraftLimit = new BigDecimal(1000);
//        BigDecimal zeroValue = new BigDecimal(0);
//
//        try {
//            toCheckingAccount = new CheckingAccount(overdraftLimit, owner);
//            fromCheckingAccount = new CheckingAccount(overdraftLimit, owner);
//            fromCheckingAccount.deposit(depositAmount);
//
//            fromCheckingAccount.transferToCheckingAccount(toCheckingAccount,depositAmount);
//        }
//        catch (Exception e){
//            Assert.fail();
//        }
//
//        Assert.assertTrue(areBigDecimalsSame(zeroValue, fromCheckingAccount.getBalance()));
//        Assert.assertTrue(areBigDecimalsSame(depositAmount, toCheckingAccount.getBalance()));
//
//    }

    @Before
    public void setup(){
        this.owner = Owner.builder()
                .address("Prag")
                .firstName("Thomas")
                .lastName("Obermeier")
                .build();
    }
}
