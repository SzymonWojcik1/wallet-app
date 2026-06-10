package com.szymonwojcik.wallet_app.repository;

import com.szymonwojcik.wallet_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
