package com.exe201.tutorlink.main.entity;

import com.exe201.tutorlink.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "TutorGrades")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TutorGrades extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", nullable = false)
    private Tutors tutor;
    private String grade;
}
