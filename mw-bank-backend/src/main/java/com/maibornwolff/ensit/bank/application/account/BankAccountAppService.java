package com.maibornwolff.ensit.bank.application.account;

import com.maibornwolff.ensit.bank.domain.model.account.Balance;
import com.maibornwolff.ensit.bank.domain.model.account.BankAccount;
import com.maibornwolff.ensit.bank.domain.model.account.Name;
import com.maibornwolff.ensit.bank.domain.service.account.BankAccounts;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class BankAccountAppService {

    private final BankAccounts bankAccounts;

    public BankAccountAppService(BankAccounts bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public Set<BankAccount> all() {
        return bankAccounts.allAccounts();
    }

    public void createAccount(CreateBankAccountCommand command) {
        BankAccount bankAccount = BankAccount.create(command.name(), command.balance());
        bankAccounts.create(bankAccount);
    }
}
