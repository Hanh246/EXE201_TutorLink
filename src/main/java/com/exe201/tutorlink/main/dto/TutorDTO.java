package com.exe201.tutorlink.main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class TutorDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String name;
    private Date birthDate;
    private String gender;
    private String learnMode;
    private String schoolName;
    private String major;
    private List<TutorSubjectDTO> subjects;
    private String strength;
    private Integer price;
    private List<TutorScheduleDTO> schedules;
    private List<TutorGradeDTO> grades;
    private List<TutorDegreeDTO> degrees;
}
