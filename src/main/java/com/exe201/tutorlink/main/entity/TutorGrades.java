package com.exe201.tutorlink.main.entity;

import com.exe201.tutorlink.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "TutorGrades")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TutorGrades extends BaseEntity {
    private String grade;
}
