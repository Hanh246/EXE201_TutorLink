package com.exe201.tutorlink.main.dto.tutor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class TutorSearchDTO{
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String name;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;
    private String gender;
    private String location;
    private List<TutorSubjectDTO> subjects;
    private Integer price;
}
