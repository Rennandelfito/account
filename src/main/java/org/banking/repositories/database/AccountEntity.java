package org.banking.repositories.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.banking.entities.StatusAccountEnum;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private Long numberAccount;
    private Long numberAgency;
    @Column(unique = true)
    private String document;
    @Enumerated(EnumType.STRING)
    private StatusAccountEnum status;

    private Double balance;
}
