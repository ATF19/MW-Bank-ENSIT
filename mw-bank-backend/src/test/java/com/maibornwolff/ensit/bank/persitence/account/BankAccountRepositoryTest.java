package com.maibornwolff.ensit.bank.persitence.account;

import com.maibornwolff.ensit.bank.domain.model.account.Balance;
import com.maibornwolff.ensit.bank.domain.model.account.BankAccount;
import com.maibornwolff.ensit.bank.domain.model.account.Name;
import com.maibornwolff.ensit.bank.domain.service.account.BankAccounts;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Test
public class BankAccountRepositoryTest {

    private BankAccount bankAccount;
    private BankAccounts bankAccounts;

    @BeforeTest
    public void setup() {
        bankAccount = BankAccount.create(new Name("Ensit", "Test"), new Balance(1000.));
        bankAccounts = new BankAccountRepository();
    }

    @Test
    public void retrieve_all_bank_accounts() {
        // given
        var account1 = BankAccount.create(new Name("Ensit", "Test"), new Balance(1000.));
        var account2 = BankAccount.create(new Name("Atef", "Test"), new Balance(1500.));
        var account3 = BankAccount.create(new Name("Ensit", "Najar"), new Balance(0010.));
        bankAccounts.create(account1);
        bankAccounts.create(account2);
        bankAccounts.create(account3);

        // when
        Set<BankAccount> result = bankAccounts.allAccounts();

        // then
        assertThat(result).containsExactlyInAnyOrder(account1, account2, account3);
    }

    @Test
    public void save_a_bank_accounts() {
        // given

        // when
        bankAccounts.create(bankAccount);

        // then
        assertThat(bankAccounts.allAccounts()).contains(bankAccount);
    }
}