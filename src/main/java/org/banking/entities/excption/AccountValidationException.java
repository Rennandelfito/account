package org.banking.entities.excption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountValidationException extends RuntimeException{
    private String  message;
}
