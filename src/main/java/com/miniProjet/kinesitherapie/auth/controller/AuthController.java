package com.miniProjet.kinesitherapie.auth.controller;

import com.miniProjet.kinesitherapie.auth.dto.LoginRequest;
import com.miniProjet.kinesitherapie.auth.dto.RegisterRequest;
import com.miniProjet.kinesitherapie.exceptions.EmailAlreadyExistsException;
import com.miniProjet.kinesitherapie.model.entities.Utilisateur;
import com.miniProjet.kinesitherapie.services.interfaces.UtilisateurService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * AuthController
 *
 * This class is a REST controller mapped to "/api/auth" endpoints.
 * It provides user authentication and registration functionality.
 *
 * <ul>
 *   <li><b>Endpoints:</b>
 *     <ul>
 *       <li><b>@PostMapping("/register")</b>:
 *         Handles user registration by accepting a JSON payload of type `RegisterRequest`.
 *         - Validates if the email already exists.
 *         - Registers the user if valid or returns an error message if not.
 *       </li>
 *       <li><b>@PostMapping("/login")</b>:
 *         Handles user login by verifying credentials using `AuthenticationManager`.
 *         - Sets authentication details in `SecurityContext`.
 *         - Saves session in Spring Session repository.
 *         - Updates the user's `loggedIn` status in the database.
 *       </li>
 *     </ul>
 *   </li>
 *   <li><b>Dependencies:</b>
 *     <ul>
 *       <li><b>UtilisateurService</b>: Manages user-related business logic.</li>
 *       <li><b>ModelMapper</b>: Converts between entities and DTOs.</li>
 *       <li><b>AuthenticationManager</b>: Handles user authentication.</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * <b>Note:</b> Accepts and responds with JSON data via @RequestBody and ResponseEntity.
 */

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UtilisateurService utilisateurService;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            Utilisateur utilisateur = utilisateurService.registerUtilisateur(registerRequest);
            return ResponseEntity.ok(modelMapper.map(utilisateur, RegisterRequest.class));
        } catch (EmailAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.motDePasse()
                )
        );

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);

        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

        Utilisateur utilisateur = utilisateurService.findByEmail(loginRequest.email());
        utilisateur.setLoggedIn(true);
        utilisateurService.saveUtilisateur(utilisateur);

        Map<String, Object> result = new HashMap<>();
        result.put("message", "Login successful!");
        result.put("email", utilisateur.getEmail());
        result.put("role", utilisateur.getRole().name());
        return ResponseEntity.ok(result);
    }
}
