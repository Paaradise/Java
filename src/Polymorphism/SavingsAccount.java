package Polymorphism;

import java.math.BigDecimal;

public class SavingsAccount extends Account {
    public SavingsAccount(String accountNumber, String balance)
    {
        this.accountNumber = accountNumber;
        this.balance = new BigDecimal(balance);
        //this account has default interest rate set to 3%
        this.interestRate = new BigDecimal("3");
    }

    public boolean balanceEquals(String amount)
    {
        return this.balance.toString().equals(amount);
    }

    public void deposit(BigDecimal amount)
    {
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount)
    {
        assert this.balance.compareTo(amount) >= 0 : "You don't have enough money to complete this operation.";
        this.balance = this.balance.subtract(amount);
    }

    public String toString()
    {
        return "Account number: " + this.accountNumber + "\nBalance: " + this.balance.toString();
    }

    @Override
    public BigDecimal calculateTax(BigDecimal income) {
        return income.multiply(new BigDecimal("0.19"));
    }

    @Override
    public BigDecimal calculateInterest(int int_k, int n) {
        BigDecimal k = new BigDecimal(int_k);
        BigDecimal divisor = k.multiply(new BigDecimal(100));
        return this.balance.multiply(this.interestRate.divide(divisor).add(BigDecimal.ONE).pow(n*int_k)).subtract(this.balance);
    }

    public BigDecimal calculateIncome(int k, int n)
    {
        BigDecimal income = calculateInterest(k, n);
        BigDecimal tax = calculateTax(income);
        return income.subtract(tax);
    }
}
