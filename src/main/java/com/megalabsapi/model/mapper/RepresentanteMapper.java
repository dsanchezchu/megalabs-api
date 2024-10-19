package com.megalabsapi.model.mapper;

import com.megalabsapi.model.dto.LoginRequestDTO;
import com.megalabsapi.model.dto.LoginResponseDTO;
import com.megalabsapi.model.entity.Representante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepresentanteMapper {

    @Autowired
    private ModelMapper modelMapper;

    public LoginResponseDTO toLoginResponseDTO(Representante representante) {
        return modelMapper.map(representante, LoginResponseDTO.class);
    }

    public Representante toEntity(LoginRequestDTO loginRequestDTO) {
        return modelMapper.map(loginRequestDTO, Representante.class);
    }
}
