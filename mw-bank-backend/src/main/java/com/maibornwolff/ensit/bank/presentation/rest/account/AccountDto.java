package com.maibornwolff.ensit.bank.presentation.rest.account;

import java.time.Instant;

public record AccountDto(String id, String firstName, String lastName, double balance,
                         Instant createdAt, Instant updatedAt) {
}
