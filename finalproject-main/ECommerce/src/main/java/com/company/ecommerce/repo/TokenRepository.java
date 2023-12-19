package com.company.ecommerce.repo;

import com.company.ecommerce.entity.Customer;
import com.company.ecommerce.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query("""
        SELECT t FROM Token t INNER JOIN Customer  c on t.user.id = c.id
        WHERE c.id = :userId AND (t.expired = false OR t.revoked = false)
    """)
    List<Token> findAllValidTokensByUser(Long userId);

    Optional<Token> findByToken(String token);

    @Query("SELECT t.user FROM Token t WHERE t.token = ?1")
    Customer findCustomerIdByToken(String token);
}
