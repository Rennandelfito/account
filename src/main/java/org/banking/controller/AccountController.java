package org.banking.controller;

import org.banking.entities.AccountResponse;
import org.banking.entities.UpdateAccountRequest;
import org.banking.entities.UpdateBalaneRequest;
import org.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping
    ResponseEntity<String> post(@RequestHeader String document) {
        service.post(document);
        return ResponseEntity.ok("ok");
    }

    @GetMapping
    ResponseEntity<AccountResponse> getAccount(@RequestHeader Map<String, String> headers) {
        AccountResponse response = service.getAccount(headers);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateStatus")
    ResponseEntity<AccountResponse> updateAccount(@RequestBody UpdateAccountRequest request) {
        AccountResponse response = service.updateAccount(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateBalance")
    ResponseEntity<AccountResponse> updateBalance(@RequestBody UpdateBalaneRequest request) {
        return ResponseEntity.ok(service.updateBalance(request));
    }
}
