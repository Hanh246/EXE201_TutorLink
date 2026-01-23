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
@Table(name = "Student")
@EqualsAndHashCode(callSuper = true)
public class Students extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = true)
    private Parents parent;
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
    @Column(name = "Grade")
    private String grade;
}
