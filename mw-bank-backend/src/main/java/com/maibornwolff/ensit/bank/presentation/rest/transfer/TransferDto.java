package com.maibornwolff.ensit.bank.presentation.rest.transfer;

import com.maibornwolff.ensit.bank.presentation.rest.account.AccountDto;

import java.time.Instant;

public record TransferDto(String id, AccountDto from, AccountDto to, double amount, Instant createdAt) {
}
