package com.megalabsapi.service.impl;

import com.megalabsapi.dto.AuthResponseDTO;
import com.megalabsapi.dto.LoginDTO;
import com.megalabsapi.dto.UserProfileDTO;
import com.megalabsapi.dto.UserRegistrationDTO;
import com.megalabsapi.model.entity.Representante;
import com.megalabsapi.model.entity.Role;
import com.megalabsapi.model.enums.ERole;
import com.megalabsapi.exception.BadRequestException;
import com.megalabsapi.exception.InvalidCredentialsException;
import com.megalabsapi.exception.ResourceNotFoundException;
import com.megalabsapi.exception.RoleNotFoundException;
import com.megalabsapi.mapper.UserMapper;
import com.megalabsapi.repository.RepresentanteRepository;
import com.megalabsapi.repository.RoleRepository;
import com.megalabsapi.security.TokenProvider;
import com.megalabsapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final RepresentanteRepository representanteRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    private static final String DNI_REGISTERED_ERROR = "El DNI ya está registrado";
    private static final String ROLE_NOT_FOUND_ERROR = "Rol no encontrado";
    private static final String USER_NOT_FOUND_ERROR = "Usuario no encontrado con el DNI proporcionado";
    private static final String INVALID_CREDENTIALS_ERROR = "Credenciales incorrectas";

    @Transactional
    public UserProfileDTO registerRepresentante(UserRegistrationDTO registrationDTO) {
        if (representanteRepository.existsByDni(registrationDTO.getDni())) {
            log.info("DNI already exists: {}", registrationDTO.getDni());
            throw new BadRequestException(DNI_REGISTERED_ERROR);
        }

        Role role = roleRepository.findByName(ERole.REPRESENTANTE)
                .orElseThrow(() -> new RoleNotFoundException(ROLE_NOT_FOUND_ERROR));

        registrationDTO.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        Representante representante = userMapper.toRepresentanteEntity(registrationDTO);
        representante.setContraseña(registrationDTO.getPassword());
        representante.setRole(role);

        log.info("Guardando nuevo representante con DNI: {}", registrationDTO.getDni());
        Representante savedRepresentante = representanteRepository.save(representante);
        log.info("Guardado nuevo representante con DNI: {}", registrationDTO.getDni());

        return userMapper.toUserProfileDTO(savedRepresentante);
    }

    @Transactional
    public AuthResponseDTO login(LoginDTO loginDTO) {
        Representante representante = representanteRepository.findByDni(loginDTO.getDni())
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_ERROR));

        if (!passwordEncoder.matches(loginDTO.getPassword(), representante.getContraseña())) {
            throw new InvalidCredentialsException(INVALID_CREDENTIALS_ERROR);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getDni(), loginDTO.getPassword())
        );

        String token = tokenProvider.createAccessToken(authentication);

        return userMapper.toAuthResponseDTO(representante, token);
    }

    @Transactional
    public UserProfileDTO updateUserProfile(String dni, UserProfileDTO userProfileDTO) {
        Representante representante = representanteRepository.findByDni(dni)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_ERROR));

        if (!representante.getEmail().equals(userProfileDTO.getEmail()) &&
                representanteRepository.existsByEmail(userProfileDTO.getEmail())) {
            throw new BadRequestException("El email ya está en uso");
        }

        representante.setNombre(userProfileDTO.getNombre());
        representante.setEmail(userProfileDTO.getEmail());
        representante.setLaboratorio(userProfileDTO.getLaboratorio());

        Representante updatedRepresentante = representanteRepository.save(representante);

        return userMapper.toUserProfileDTO(updatedRepresentante);
    }

    @Transactional
    public UserProfileDTO getUserProfileByDni(String dni) {
        Representante representante = representanteRepository.findByDni(dni)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_ERROR));

        return userMapper.toUserProfileDTO(representante);
    }
}
