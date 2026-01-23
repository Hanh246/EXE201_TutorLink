package com.exe201.tutorlink.main.dto;

import com.exe201.tutorlink.main.entity.Subjects;
import com.exe201.tutorlink.main.entity.TutorGrades;
import com.exe201.tutorlink.main.entity.TutorSchedules;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class TutorDTO {
    private Long id;
    private String name;
    private Date birthDate;
    private String gender;
    private String learnMode;
    private String schoolName;
    private String major;
    private List<Subjects> subjects;
    private String strength;
    private Integer price;
    private List<TutorSchedules> schedules;
    private List<TutorGrades> grades;
}
