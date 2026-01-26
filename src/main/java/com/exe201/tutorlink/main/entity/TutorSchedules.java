package com.exe201.tutorlink.main.entity;

import com.exe201.tutorlink.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "TutorSchedule")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TutorSchedules extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", nullable = false)
    private Tutors tutor;
    @Column(name = "DayOfWeek")
    private String dayOfWeek;
    @Column(name = "StartTime")
    private String startTime;
    @Column(name = "EndTime")
    private String endTime;
    @Column(name = "Shift")
    private String shift;
}
