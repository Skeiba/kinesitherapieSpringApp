package com.miniProjet.kinesitherapie.utils;

import com.miniProjet.kinesitherapie.exceptions.EmailDontExistException;
import com.miniProjet.kinesitherapie.model.entities.Utilisateur;
import com.miniProjet.kinesitherapie.services.interfaces.UtilisateurService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    private final UtilisateurService utilisateurService;
    private final ModelMapper modelMapper;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            log.warn("No authenticated user found during logout");
            return;
        }

        String email = authentication.getName();
        try {
            Utilisateur utilisateur = utilisateurService.findByEmail(email);
            utilisateurService.updateLoggedInStatus(utilisateur.getId(), false);
            log.info("User {} logged out successfully", email);
        } catch (EmailDontExistException e) {
            log.error("Logout failed: User {} not found", email);
        }
    }

}
