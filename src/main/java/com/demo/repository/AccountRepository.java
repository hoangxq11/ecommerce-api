package com.demo.repository;

import com.demo.model.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    @EntityGraph(attributePaths = "authorities")
    Optional<Account> findOneWithAuthoritiesByEmail(String email);
    @EntityGraph(attributePaths = "authorities")
    Optional<Account> findOneWithAuthoritiesByUsername(String username);
    Boolean existsByUsernameOrEmail(String username, String email);

}
