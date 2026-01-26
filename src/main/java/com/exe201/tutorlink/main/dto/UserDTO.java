package com.exe201.tutorlink.main.dto;

import com.exe201.tutorlink.main.constants.RoleEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String email;
    private String phone;
    private String passwordHash;
    private RoleEnum role;
}
