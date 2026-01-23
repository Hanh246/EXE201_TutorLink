package com.exe201.tutorlink.main.entity;

import com.exe201.tutorlink.common.entity.BaseEntity;
import com.exe201.tutorlink.main.constants.RoleEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "User")
@EqualsAndHashCode(callSuper = true)
public class Users extends BaseEntity {
    @Column(name = "Email")
    private String email;
    @Column(name = "Phone")
    private String phone;
    @Column(name = "PasswordHash")
    private String passwordHash;
    @Column(name = "Role")
    private RoleEnum role;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Tutors tutor;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Students student;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Parents parent;
}
