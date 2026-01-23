package com.exe201.tutorlink.main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    private String name;
    private Date birthDate;
    private String gender;
    private String learnMode;
    private String schoolName;
    private String grade;
}
