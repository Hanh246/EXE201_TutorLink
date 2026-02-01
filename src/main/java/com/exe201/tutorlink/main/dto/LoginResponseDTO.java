package com.exe201.tutorlink.main.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDTO {
    private String accessToken;
    private String refreshToken;
    private UserBaseDTO userDetails;
}
