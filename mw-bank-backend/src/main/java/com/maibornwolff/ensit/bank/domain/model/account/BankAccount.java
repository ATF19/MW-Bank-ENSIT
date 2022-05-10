package com.maibornwolff.ensit.bank.domain.model.account;

import java.util.UUID;

public class BankAccount {
    private final BankAccountId id;
    private Name name;
    private Balance balance;

    public BankAccount(BankAccountId id, Name name, Balance balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public static BankAccount create(Name name, Balance balance) {
        verifyBalanceIsNotNegative(balance);
        BankAccountId id = new BankAccountId(UUID.randomUUID());
        return new BankAccount(id, name, balance);
    }

    private static void verifyBalanceIsNotNegative(Balance balance) {
        if (balance.balance() < 0)
            throw new IllegalStateException();
    }

    public BankAccountId getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Balance getBalance() {
        return balance;
    }
}
