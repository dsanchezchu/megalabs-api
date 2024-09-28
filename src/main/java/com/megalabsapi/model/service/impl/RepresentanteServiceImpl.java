package com.megalabsapi.model.service.impl;

import com.megalabsapi.model.dto.LoginRequestDTO;
import com.megalabsapi.model.dto.LoginResponseDTO;
import com.megalabsapi.model.entity.Representante;
import com.megalabsapi.model.repository.RepresentanteRepository;
import com.megalabsapi.model.service.RepresentanteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RepresentanteServiceImpl implements RepresentanteService {

    @Autowired
    private RepresentanteRepository representanteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Representante registrarRepresentante(Representante representante) {
        // Cifrar la contraseña antes de guardarla
        String contraseñaCifrada = passwordEncoder.encode(representante.getContraseña());
        representante.setContraseña(contraseñaCifrada);

        // Guardar el representante en la base de datos
        return representanteRepository.save(representante);
    }

    @Override
    public LoginResponseDTO autenticarUsuario(LoginRequestDTO loginRequestDTO) {
        Representante representante = representanteRepository.findByDni(loginRequestDTO.getDni());

        if (representante == null || !passwordEncoder.matches(loginRequestDTO.getPassword(), representante.getContraseña())) {
            throw new RuntimeException("DNI o contraseña incorrectos");
        }

        // Convertimos el representante a un LoginResponseDTO usando ModelMapper
        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setNombre(representante.getNombre());
        responseDTO.setMensaje("Inicio de sesión exitoso");

        return responseDTO;
    }
}

