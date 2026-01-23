package com.exe201.tutorlink.main.dto;

import com.exe201.tutorlink.main.constants.RoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private String email;
    private String phone;
    private String passwordHash;
    private RoleEnum role;
}
