package com.maibornwolff.ensit.bank.domain.model.account;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BankAccountTest {

    private Name name;
    private Balance balance;

    @BeforeTest
    public void setup() {
        name = new Name("Ensit", "Workshop");
        balance = new Balance(1000.);
    }

    @Test
    public void create_a_bank_account() {
        // given

        // when
        BankAccount result = BankAccount.create(name, balance);

        // then
        assertThat(result.getName()).isEqualTo(name);
        assertThat(result.getBalance()).isEqualTo(balance);
    }

    @Test
    public void cannot_create_an_account_with_negative_balance() {
        // given
        balance = new Balance(-1000.);

        // when

        // then
        assertThatThrownBy(() -> BankAccount.create(name, balance))
                .isInstanceOf(IllegalStateException.class);
    }
}