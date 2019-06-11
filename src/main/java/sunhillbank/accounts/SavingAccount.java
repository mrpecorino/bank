package sunhillbank.accounts;

import sunhillbank.Owner;

import lombok.NonNull;

import java.math.BigDecimal;

public class SavingAccount extends Account{
    private BigDecimal interestRate;

    public SavingAccount(@NonNull Owner owner, @NonNull double interestRate) throws Exception{
        super(owner);
        setInterestRate(interestRate);
    }

    public void payInterest() throws Exception{
        BigDecimal interest = calculateInterest();
        deposit(interest);
    }

    private BigDecimal calculateInterest(){
        return this.balance.multiply(this.interestRate);
    }

    public void setInterestRate(@NonNull double interestRate) throws Exception{
        checkInterestValidity(interestRate);
        BigDecimal interestRateDouble = BigDecimal.valueOf(interestRate);
        this.interestRate = interestRateDouble;
    }
    
    private void checkInterestValidity(double interest)throws Exception{
        if (interest < 0 || interest > 1){
            throw new Exception("Interest rate is invalid");
        }
    }
}