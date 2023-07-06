package org.banking.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBalaneRequest {
    private String document;
    private Long numberAccount;
    private Double amount;
    private BalanceActionEnum action;
}
