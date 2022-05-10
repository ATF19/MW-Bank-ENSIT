package com.maibornwolff.ensit.bank.presentation.rest.account;

import com.maibornwolff.ensit.bank.application.account.BankAccountAppService;
import com.maibornwolff.ensit.bank.application.account.CreateBankAccountCommand;
import com.maibornwolff.ensit.bank.domain.model.account.Balance;
import com.maibornwolff.ensit.bank.domain.model.account.Name;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/account")
public class AccountRestService {

    private static final Logger logger = Logger.getLogger(AccountRestService.class.getName());
    private final BankAccountAppService bankAccountAppService;

    public AccountRestService(BankAccountAppService bankAccountAppService) {
        this.bankAccountAppService = bankAccountAppService;
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> allAccounts() {
        logger.info("Retrieving all accounts.");
        List<AccountDto> dtos = bankAccountAppService.all()
                .stream()
                .map(account -> new AccountDto(account.getId().id().toString(), account.getName().firstName(),
                        account.getName().lastName(), account.getBalance().balance(), Instant.now(), Instant.now()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody CreateAccountDto dto) {
        logger.info("Creating account with the following: " + dto);
        bankAccountAppService.createAccount(new CreateBankAccountCommand(new Name(dto.firstName(), dto.lastName()),
                new Balance(dto.balance())));
        return ResponseEntity.ok().build();
    }
}
