package org.banking.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private Long id;
    private Long numberAccount;
    private Long numberAgency;
    private String document;
    private StatusAccountEnum status;
    private Double balance;

}
