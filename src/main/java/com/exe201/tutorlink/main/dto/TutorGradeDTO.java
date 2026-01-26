package com.exe201.tutorlink.main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TutorGradeDTO{
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String grade;
}
