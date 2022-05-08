package com.maibornwolff.ensit.bank.presentation.rest.transfer;

import com.maibornwolff.ensit.bank.presentation.rest.account.AccountDto;
import com.maibornwolff.ensit.bank.presentation.rest.account.AccountRestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/transfer")
public class TransferRestService {

    private static final Logger logger = Logger.getLogger(AccountRestService.class.getName());

    @GetMapping
    public ResponseEntity<List<TransferDto>> allTransfers() {
        logger.info("Retrieving all transfers.");
        // TODO get real accounts
        List<TransferDto> transferDtos = getDummyTransferDtos();
        return ResponseEntity.ok(transferDtos);
    }

    @PostMapping
    public ResponseEntity<TransferDto> createTransfer(@RequestBody CreateTransferDto createTransferDto) {
        logger.info("Creating transfer with the following: " + createTransferDto);
        // TODO create a transfer
        return ResponseEntity.ok().build();
    }

    private List<TransferDto> getDummyTransferDtos() {
        AccountDto account1 = new AccountDto(UUID.randomUUID().toString(), "John", "Doe", 1000., Instant.now(), Instant.now());
        AccountDto account2 = new AccountDto(UUID.randomUUID().toString(), "Marry", "Jane", 1500., Instant.now(), Instant.now());
        return List.of(
                new TransferDto(UUID.randomUUID().toString(), account1, account2, 100., Instant.now()),
                new TransferDto(UUID.randomUUID().toString(), account2, account1, 10., Instant.now()),
                new TransferDto(UUID.randomUUID().toString(), account1, account2, 90., Instant.now()),
                new TransferDto(UUID.randomUUID().toString(), account2, account1, 15., Instant.now())
        );
    }
}
