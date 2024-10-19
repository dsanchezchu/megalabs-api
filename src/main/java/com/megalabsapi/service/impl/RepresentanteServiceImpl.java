package com.megalabsapi.model.service.impl;

import com.megalabsapi.dto.LoginRequestDTO;
import com.megalabsapi.entity.Representante;
import com.megalabsapi.mapper.LoginMapper;
import com.megalabsapi.mapper.RepresentanteMapper;
import com.megalabsapi.repository.RepresentanteRepository;
import com.megalabsapi.model.service.RepresentanteService;
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
    private RepresentanteMapper representanteMapper;

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Representante registrarRepresentante(Representante representante) {
        // Cifrar la contraseña antes de guardarla
        representante.setContraseña(passwordEncoder.encode(representante.getContraseña()));
        return representanteRepository.save(representante);
    }

    @Override
    public Representante autenticarUsuario(LoginRequestDTO loginRequestDTO) {
        Representante representante = representanteRepository.findByDni(loginRequestDTO.getDni());

        if (representante == null) {
            throw new IllegalArgumentException("DNI incorrecto");
        }

        // Verificación de contraseña
        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), representante.getContraseña())) {
            representante.setIntentosFallidos(representante.getIntentosFallidos() + 1);
            representanteRepository.save(representante);
            throw new IllegalArgumentException("Contraseña incorrecta");
        }

        // Reiniciar intentos fallidos en caso de autenticación exitosa
        representante.setIntentosFallidos(0);
        representanteRepository.save(representante);

        // Retorna el representante autenticado
        return representante;
    }

    public void actualizarCredenciales(Representante representante, String nuevaContraseña, String nuevoEmail) {
        // Actualizar la contraseña, cifrando la nueva contraseña
        representante.setContraseña(passwordEncoder.encode(nuevaContraseña));

        // Actualizar el correo electrónico
        representante.setEmail(nuevoEmail);

        // Guardar los cambios
        representanteRepository.save(representante);
    }

    @Override
    public Representante findByDni(String dni) {
        return representanteRepository.findByDni(dni);  // Buscar representante por su dni en el repositorio
    }

}
