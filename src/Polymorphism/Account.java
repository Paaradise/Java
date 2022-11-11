package Polymorphism;

import java.math.BigDecimal;

public abstract class Account implements Tax, Interest {
    String accountNumber;
    //balance must be BigInteger to avoid floating point operations.
    BigDecimal balance;
    BigDecimal interestRate;

    public abstract void deposit(BigDecimal amount);
    public abstract void withdraw(BigDecimal amount);
}
