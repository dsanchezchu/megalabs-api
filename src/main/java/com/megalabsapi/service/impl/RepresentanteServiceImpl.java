package com.megalabsapi.service.impl;

import com.megalabsapi.dto.LoginRequestDTO;
import com.megalabsapi.model.entity.Representante;
import com.megalabsapi.mapper.LoginMapper;
import com.megalabsapi.mapper.RepresentanteMapper;
import com.megalabsapi.repository.RepresentanteRepository;
import com.megalabsapi.service.RepresentanteService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RepresentanteServiceImpl implements RepresentanteService {

    private final RepresentanteRepository representanteRepository;
    private final PasswordEncoder passwordEncoder;
    private final RepresentanteMapper representanteMapper;
    private final LoginMapper loginMapper;

    private static final String DNI_ERROR = "DNI incorrecto";
    private static final String PASSWORD_ERROR = "Contraseña incorrecta";
    private static final String EMAIL_DUPLICATE_ERROR = "El email ya está en uso";

    public RepresentanteServiceImpl(RepresentanteRepository representanteRepository,
                                    PasswordEncoder passwordEncoder,
                                    RepresentanteMapper representanteMapper,
                                    LoginMapper loginMapper) {
        this.representanteRepository = representanteRepository;
        this.passwordEncoder = passwordEncoder;
        this.representanteMapper = representanteMapper;
        this.loginMapper = loginMapper;
    }

    @Override
    public Representante registrarRepresentante(Representante representante) {
        representante.setContraseña(passwordEncoder.encode(representante.getContraseña()));
        return representanteRepository.save(representante);
    }

    @Override
    public Representante autenticarUsuario(LoginRequestDTO loginRequestDTO) {
        Representante representante = representanteRepository.findByDni(loginRequestDTO.getDni())
                .orElseThrow(() -> new IllegalArgumentException(DNI_ERROR));

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), representante.getContraseña())) {
            representante.setIntentosFallidos(representante.getIntentosFallidos() + 1);
            representanteRepository.save(representante);
            throw new IllegalArgumentException(PASSWORD_ERROR);
        }

        representante.setIntentosFallidos(0);
        representanteRepository.save(representante);

        return representante;
    }

    public void actualizarCredenciales(Representante representante, String nuevaContraseña, String nuevoEmail) {
        if (representanteRepository.findByEmail(nuevoEmail).isPresent()) {
            throw new IllegalArgumentException(EMAIL_DUPLICATE_ERROR);
        }

        representante.setContraseña(passwordEncoder.encode(nuevaContraseña));
        representante.setEmail(nuevoEmail);
        representanteRepository.save(representante);
    }

    @Override
    public Representante findByDni(String dni) {
        return representanteRepository.findByDni(dni)
                .orElseThrow(() -> new IllegalArgumentException(DNI_ERROR));
    }

    @Override
    public Representante save(Representante representante) {
        return representanteRepository.save(representante);
    }
}