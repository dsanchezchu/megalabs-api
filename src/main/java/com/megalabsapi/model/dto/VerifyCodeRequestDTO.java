package com.megalabsapi.model.dto;

import lombok.Data;
import jakarta.validation.constraints.NotEmpty;

@Data
public class VerifyCodeRequestDTO {

    @NotEmpty(message = "El código de verificación es obligatorio")
    private String code;

    @NotEmpty(message = "El ID del representante es obligatorio")
    private Long representanteId;
}

