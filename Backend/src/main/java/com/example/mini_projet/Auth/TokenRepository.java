package com.example.mini_projet.Auth;

import java.util.List;
import java.util.Optional;

import com.example.mini_projet.Auth.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
      select t from Token t inner join User u\s
      on t.user.cin = u.cin\s
      where u.cin = :cin and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(String cin);

    Optional<Token> findByToken(String token);
}
