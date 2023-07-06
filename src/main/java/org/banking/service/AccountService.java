package org.banking.service;

import org.banking.entities.*;
import org.banking.entities.excption.AccountValidationException;
import org.banking.repositories.database.AccountEntity;
import org.banking.repositories.database.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    static final Long NUMBER_AGENCY = 15L;
    static final Double INITIAL_BALANCE = 0.00;

    public void post(String document) {
        Long numberAccount = generateNumberAccount();
        Long numberAgency = NUMBER_AGENCY;
        StatusAccountEnum statusAccount = StatusAccountEnum.ACTIVE;

        AccountEntity account = new AccountEntity(null, numberAccount,
                numberAgency, document, statusAccount, INITIAL_BALANCE);

        accountRepository.save(account);
    }

    public AccountResponse getAccount(Map<String, String> headers) {
        Optional<AccountEntity> account = null;

        String document = headers.get("document");
        Long numberAccount = null;
        try {
            numberAccount = Long.parseLong(headers.get("numberaccount"));
        } catch (Exception ex) {
            numberAccount = null;
        }

        AccountEntity accountEntity = getByDocumenteOrNumberAccunt(numberAccount, document);

        return mapToAccountResponse(accountEntity);
    }

    public AccountResponse updateAccount(UpdateAccountRequest request) {
        String document = request.getDocument();
        Long numberAccount = generateNumberAccount();
        StatusAccountEnum status = request.getStatusAccount();
        AccountEntity accountEntity = getByDocumenteOrNumberAccunt(numberAccount, document);
        accountEntity.setStatus(status);
        AccountResponse response = mapToAccountResponse(accountRepository.save(accountEntity));
        return response;
    }

    public AccountResponse updateBalance(UpdateBalaneRequest request) {
        String document = request.getDocument();
        Long numberAccount = request.getNumberAccount();
        BalanceActionEnum action = request.getAction();
        Double amount = request.getAmount();
        AccountEntity accountEntity = getByDocumenteOrNumberAccunt(numberAccount, document);
        Double balance = accountEntity.getBalance();
        if (action == BalanceActionEnum.DEPOSIT) {
           balance =  accountEntity.getBalance() + amount;
        }else {
            validateBalance(amount, accountEntity);
            balance =  accountEntity.getBalance() - amount;
        }
        accountEntity.setBalance(balance);
        accountRepository.save(accountEntity);

        return mapToAccountResponse(accountEntity);
    }

    private static void validateBalance(Double amount, AccountEntity accountEntity) {
        if (amount > accountEntity.getBalance() ){
            throw new AccountValidationException("There is no balance enough");
        }
    }

    private Long generateNumberAccount() {
        Random random = new Random();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            Integer number = random.nextInt(10);
            if (number == 0 && i == 0) {
                number++;
            }
            str.append(number);
        }
        return Long.parseLong(str.toString());
    }

    private AccountEntity getByDocumenteOrNumberAccunt(Long numberAccount, String document) {
        Optional<AccountEntity> account = null;
        if (document != null) {
            account = accountRepository.findByDocument(document);
        } else if (numberAccount != null) {
            account = accountRepository.findByNumberAccount(numberAccount);
        }
        if (!account.isPresent()) {
            throw new AccountValidationException("Account not found");
        }
        return account.get();
    }

    private AccountResponse mapToAccountResponse(AccountEntity entity) {
        return new AccountResponse(entity.getId(), entity.getNumberAccount(),
                entity.getNumberAgency(),
                entity.getDocument(), entity.getStatus(), entity.getBalance());
    }
}
