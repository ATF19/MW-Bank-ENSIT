package com.maibornwolff.ensit.bank.domain.service.account;

import com.maibornwolff.ensit.bank.domain.model.account.BankAccount;

import java.util.Set;

public interface BankAccounts {
    void create(BankAccount bankAccount);
    Set<BankAccount> allAccounts();
}
