package com.exe201.tutorlink.main.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StudentDTO extends UserBaseDTO{
    private String learnMode;
    private String schoolName;
    private String grade;
}
