package com.maibornwolff.ensit.bank.presentation.rest.account;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/account")
public class AccountRestService {

    private static final Logger logger = Logger.getLogger(AccountRestService.class.getName());

    @GetMapping
    public ResponseEntity<List<AccountDto>> allAccounts() {
        logger.info("Retrieving all accounts.");
        // TODO get real accounts
        List<AccountDto> accountDtos = getDummyAccountDtos();
        return ResponseEntity.ok(accountDtos);
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody CreateAccountDto createAccountDto) {
        logger.info("Creating account with the following: " + createAccountDto);
        // TODO create an account
        return ResponseEntity.ok().build();
    }

    private List<AccountDto> getDummyAccountDtos() {
        return List.of(
                new AccountDto(UUID.randomUUID().toString(), "John", "Doe", 1000., Instant.now(), Instant.now()),
                new AccountDto(UUID.randomUUID().toString(), "Marry", "Jane", 1500., Instant.now(), Instant.now()),
                new AccountDto(UUID.randomUUID().toString(), "Another", "User", 0., Instant.now(), Instant.now()),
                new AccountDto(UUID.randomUUID().toString(), "Mr", "Bond", 10000., Instant.now(), Instant.now()),
                new AccountDto(UUID.randomUUID().toString(), "Another", "John", 15., Instant.now(), Instant.now()),
                new AccountDto(UUID.randomUUID().toString(), "John", "Again", 900., Instant.now(), Instant.now())
        );
    }
}
