package com.exe201.tutorlink.main.dto.tutor;

import com.exe201.tutorlink.main.dto.UserBaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TutorDTO extends UserBaseDTO {
    private String learnMode;
    private String schoolName;
    private String major;
    private String location;
    private List<TutorSubjectDTO> subjects;
    private String strength;
    private Integer price;
    private List<TutorScheduleDTO> schedules;
    private List<TutorGradeDTO> grades;
    private List<TutorDegreeDTO> degrees;
}
