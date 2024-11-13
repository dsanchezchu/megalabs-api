package com.megalabsapi.security;

import com.megalabsapi.model.entity.Representante;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {

    private String dni;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(String dni, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.dni = dni;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(Representante representante) {
        // Obtener y asignar el rol del representante
        String roleName = representante.getRole() != null ? representante.getRole().getName().name() : "REPRESENTANTE";
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + roleName);

        return new UserPrincipal(
                representante.getDni(),
                representante.getEmail(),
                representante.getContraseña(),
                Collections.singletonList(authority)
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return dni; // Cambiado para usar DNI como identificador
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
