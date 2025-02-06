package com.miniProjet.kinesitherapie.auth.model;

import com.miniProjet.kinesitherapie.model.enums.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * CustomUserDetails
 * Implements `UserDetails` for Spring Security authentication and authorization.
 *
 * <ul>
 *   <li><b>Fields:</b>
 *     <ul>
 *       <li>`email`: User's email (username).</li>
 *       <li>`motDePasse`: User's password.</li>
 *       <li>`role`: User's role for granted authorities.</li>
 *     </ul>
 *   </li>
 *   <li><b>Methods:</b>
 *     <ul>
 *       <li>`getAuthorities()`: Returns authorities based on role.</li>
 *       <li>`getUsername()`, `getPassword()`: Provide user credentials.</li>
 *       <li>Account status methods return `true` (always valid).</li>
 *     </ul>
 *   </li>
 * </ul>
 *
 * Serializable for session management.
 */



@AllArgsConstructor
public class CustomUserDetails implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String email;
    private final String motDePasse;
    private final Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+role.name()));
    }

    @Override
    public String getPassword() {
        return motDePasse;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
