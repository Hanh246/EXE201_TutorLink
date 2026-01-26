package com.exe201.tutorlink.main.entity;

import com.exe201.tutorlink.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TutorSubject")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TutorSubjects extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", nullable = false)
    private Tutors tutor;
    private String subjectName;
}
