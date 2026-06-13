package com.szymonwojcik.wallet_app.repository;

import com.szymonwojcik.wallet_app.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
