package com.megalabsapi.mapper;

import com.megalabsapi.dto.VerifyCodeRequestDTO;
import com.megalabsapi.model.entity.Representante;
import com.megalabsapi.model.entity.VerificationCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

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

    public VerificationCode toVerificationCode(String code, Timestamp expiryDate, Representante representante) {
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setCode(code);
        verificationCode.setExpiryDate(expiryDate);
        verificationCode.setRepresentante(representante);
        verificationCode.setIsUsed(false);
        return verificationCode;
    }
}
