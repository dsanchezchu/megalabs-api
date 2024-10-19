package com.megalabsapi.model.mapper;

import com.megalabsapi.model.dto.LoginRequestDTO;
import com.megalabsapi.model.dto.LoginResponseDTO;
import com.megalabsapi.model.entity.Representante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginMapper {

    @Autowired
    private ModelMapper modelMapper;

    // Método para convertir de LoginRequestDTO a Representante
    public Representante toEntity(LoginRequestDTO loginRequestDTO) {
        return modelMapper.map(loginRequestDTO, Representante.class);
    }

    // Método para convertir de Representante a LoginResponseDTO
    public LoginResponseDTO toLoginResponseDTO(Representante representante) {
        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setNombre(representante.getNombre());  // Mapea el nombre del representante
        responseDTO.setMensaje("Inicio de sesión exitoso"); // Mensaje fijo o personalizado
        return responseDTO;
    }
}


