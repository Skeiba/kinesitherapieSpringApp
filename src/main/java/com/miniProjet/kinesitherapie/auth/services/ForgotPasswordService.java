package com.miniProjet.kinesitherapie.auth.services;

import com.miniProjet.kinesitherapie.auth.dto.ResetPasswordDTO;
import com.miniProjet.kinesitherapie.auth.model.VerificationToken;
import com.miniProjet.kinesitherapie.exceptions.EmailDontExistException;
import com.miniProjet.kinesitherapie.model.entities.Utilisateur;
import com.miniProjet.kinesitherapie.model.repositories.UtilisateurRepository;
import com.miniProjet.kinesitherapie.model.repositories.VerificationTokenRepository;
import com.miniProjet.kinesitherapie.utils.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class ForgotPasswordService {
    public static final int CODE_LENGTH = 6;
    public static final int CODE_EXPIRATION_MINUTES = 10;

    private final VerificationTokenRepository tokenRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailSenderService emailSenderService;

    public void generateAndSendVerificationCode(String email) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new EmailDontExistException("Utilisateur " + email + " does not exist"));

        String code = generateRandomCode();
        LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(CODE_EXPIRATION_MINUTES);

        tokenRepository.save(new VerificationToken(null, email, code, expiryDate, false));

        String subject = "Verification Code";
        String message = "Your verification code has been generated : "+code+"\nIt expires in: "+CODE_EXPIRATION_MINUTES+" minutes";

        emailSenderService.sendPasswordResetEmail(email, subject, message);
    }

    private String generateRandomCode() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }

    public boolean verifyCode(String email, String verificationCode) {
        VerificationToken token = tokenRepository.findByEmailAndCodeAndUsedFalse(email, verificationCode)
                .orElseThrow(() -> new IllegalArgumentException("Invalid verification code"));

        if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Verification code is expired");
        }

        token.setUsed(true);
        tokenRepository.save(token);
        return true;
    }

    public void resetPassword(ResetPasswordDTO resetPasswordDTO) {
        if (!resetPasswordDTO.getNewPassword().equals(resetPasswordDTO.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        Utilisateur utilisateur = utilisateurRepository.findByEmail(resetPasswordDTO.getEmail())
                .orElseThrow(() -> new EmailDontExistException("Utilisateur " + resetPasswordDTO.getEmail() + " does not exist"));

        utilisateur.setMotDePasse(passwordEncoder.encode(resetPasswordDTO.getNewPassword()));
        utilisateurRepository.save(utilisateur);
    }
}
