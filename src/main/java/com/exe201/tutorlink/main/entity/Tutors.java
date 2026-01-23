package com.exe201.tutorlink.main.entity;

import com.exe201.tutorlink.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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
    @JoinTable(
            name = "tutor_subject",
            joinColumns = @JoinColumn(name = "tutor_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subjects> subjects;
    @Column(name = "Strength")
    private String strength;
    @Column(name = "Price")
    private Integer price;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "tutor_id")
    private List<TutorSchedules> schedules;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "tutor_id")
    private List<TutorGrades> grades;
}
