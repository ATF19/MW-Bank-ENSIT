package com.maibornwolff.ensit.bank.application.account;

import com.maibornwolff.ensit.bank.domain.model.account.Balance;
import com.maibornwolff.ensit.bank.domain.model.account.Name;

public record CreateBankAccountCommand(Name name, Balance balance) {
}
