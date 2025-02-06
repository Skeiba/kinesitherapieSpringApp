package com.miniProjet.kinesitherapie.auth.controller;

import com.miniProjet.kinesitherapie.auth.dto.EmailRequestDTO;
import com.miniProjet.kinesitherapie.auth.dto.ResetPasswordDTO;
import com.miniProjet.kinesitherapie.auth.dto.VerificationDTO;
import com.miniProjet.kinesitherapie.auth.services.ForgotPasswordService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/forgot-password")
@RequiredArgsConstructor
public class ForgotPasswordController {


    private final ForgotPasswordService forgotPasswordService;

    @PostMapping("/request-code")
    public ResponseEntity<String> requestCode(@RequestBody @Valid EmailRequestDTO requestDTO) {
        forgotPasswordService.generateAndSendVerificationCode(requestDTO.getEmail());
        return ResponseEntity.ok("Verification code sent to email");
    }

    @PostMapping("/verify-code")
    public ResponseEntity<String> verifyCode(@RequestBody @Valid VerificationDTO verificationDTO, HttpSession session) {
        forgotPasswordService.verifyCode(verificationDTO.getEmail(), verificationDTO.getVerificationCode());
        session.setAttribute("emailVerified", verificationDTO.getEmail());
        session.setMaxInactiveInterval(15 * 60); // 15 minutes
        return ResponseEntity.ok("Verification successful");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody @Valid ResetPasswordDTO resetPasswordDTO, HttpSession session) {
        String verifiedEmail = (String) session.getAttribute("emailVerified");
        if (verifiedEmail == null || !verifiedEmail.equals(resetPasswordDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not verified or session expired");
        }
        forgotPasswordService.resetPassword(resetPasswordDTO);
        session.removeAttribute("emailVerified");
        return ResponseEntity.ok("Password reset successful");
    }
}
