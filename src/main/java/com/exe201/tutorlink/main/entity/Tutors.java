package com.exe201.tutorlink.main.entity;

import com.exe201.tutorlink.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Tutor")
@EqualsAndHashCode(callSuper = true)
public class Tutors extends BaseEntity {
    @OneToOne
    @MapsId // Dùng chung Primary Key với Entity Users
    @JoinColumn(name = "user_id")
    private Users user;
    @Column(name = "Name")
    private String name;
    @Column(name = "BirthDate")
    private Date birthDate;
    @Column(name = "Gender")
    private String gender;
    @Column(name = "LearnMode")
    private String learnMode;
    @Column(name = "SchoolName")
    private String schoolName;
    @Column(name = "Major")
    private String major;
    @Column(name = "Location")
    private String location;

    @Column(name = "Strength")
    private String strength;

    @Column(name = "Price")
    private Integer price;
}
