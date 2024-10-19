package com.megalabsapi.mapper;

import com.megalabsapi.dto.RecoverPasswordRequestDTO;
import com.megalabsapi.entity.Representante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecoverPasswordMapper {

    @Autowired
    private ModelMapper modelMapper;

    public RecoverPasswordRequestDTO toRecoverPasswordRequestDTO(Representante representante) {
        return modelMapper.map(representante, RecoverPasswordRequestDTO.class);
    }

    public Representante toEntity(RecoverPasswordRequestDTO recoverPasswordRequestDTO) {
        return modelMapper.map(recoverPasswordRequestDTO, Representante.class);
    }
}
