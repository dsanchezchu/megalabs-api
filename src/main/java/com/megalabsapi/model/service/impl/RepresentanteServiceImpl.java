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
        if (representante == null) {
            throw new RuntimeException("DNI incorrecto");
        }

        if (representante.getIntentosFallidos() >= 3) {
            throw new RuntimeException("La cuenta está bloqueada. Por favor, contacte al soporte.");
        }

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), representante.getContraseña())) {
            representante.setIntentosFallidos(representante.getIntentosFallidos() + 1);
            representanteRepository.save(representante);
            throw new RuntimeException("Contraseña incorrecta");
        }

        // Reiniciar intentos fallidos después de una autenticación exitosa
        representante.setIntentosFallidos(0);
        representanteRepository.save(representante);

        // Convertimos el representante a un LoginResponseDTO usando ModelMapper
        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setNombre(representante.getNombre());
        responseDTO.setMensaje("Inicio de sesión exitoso");

        return responseDTO;
    }
}

