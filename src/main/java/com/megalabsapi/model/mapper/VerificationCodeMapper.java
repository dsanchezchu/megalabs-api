package com.megalabsapi.model.mapper;

import com.megalabsapi.model.dto.VerifyCodeRequestDTO;
import com.megalabsapi.model.entity.VerificationCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerificationCodeMapper {

    @Autowired
    private ModelMapper modelMapper;

    public VerifyCodeRequestDTO toVerifyCodeRequestDTO(VerificationCode verificationCode) {
        return modelMapper.map(verificationCode, VerifyCodeRequestDTO.class);
    }

    public VerificationCode toEntity(VerifyCodeRequestDTO verifyCodeRequestDTO) {
        return modelMapper.map(verifyCodeRequestDTO, VerificationCode.class);
    }
}
