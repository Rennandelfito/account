package org.banking.repositories.database;

import org.banking.entities.StatusAccountEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {


    Optional<AccountEntity> findByDocument(String document);
    Optional<AccountEntity> findByNumberAccount(Long numberAccount);

    List<AccountEntity> findAllByStatus(StatusAccountEnum status);
}
