package com.miniProjet.kinesitherapie.model.repositories;

import com.miniProjet.kinesitherapie.auth.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByEmailAndCodeAndUsedFalse(String email, String code);
}
