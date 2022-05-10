package com.maibornwolff.ensit.bank.persitence.account;

import com.maibornwolff.ensit.bank.domain.model.account.BankAccount;
import com.maibornwolff.ensit.bank.domain.service.account.BankAccounts;

import java.util.HashSet;
import java.util.Set;

public class BankAccountRepository implements BankAccounts {

    private Set<BankAccount> bankAccounts = new HashSet<>();

    @Override
    public void create(BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
    }

    @Override
    public Set<BankAccount> allAccounts() {
        return bankAccounts;
    }
}
