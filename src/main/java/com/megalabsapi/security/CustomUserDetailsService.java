package com.megalabsapi.security;

import com.megalabsapi.model.entity.Representante;
import com.megalabsapi.repository.RepresentanteRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final RepresentanteRepository representanteRepository;

    public CustomUserDetailsService(RepresentanteRepository representanteRepository) {
        this.representanteRepository = representanteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
        Representante representante = representanteRepository.findByDni(dni)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el DNI: " + dni));

        // Convertir el objeto Representante a UserDetails
        return UserPrincipal.create(representante);
    }
}
