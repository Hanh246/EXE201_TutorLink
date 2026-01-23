package com.exe201.tutorlink.main.entity;

import com.exe201.tutorlink.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "TutorSchedule")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TutorSchedules extends BaseEntity {
    @Column(name = "DayOfWeek")
    private String dayOfWeek;
    @Column(name = "StartTime")
    private String startTime;
    @Column(name = "EndTime")
    private String endTime;
    @Column(name = "Shift")
    private String shift;
}
