package com.maibornwolff.ensit.bank.presentation.rest.transfer;

public record CreateTransferDto(String fromAccountId, String toAccountId, double amount) {
}
