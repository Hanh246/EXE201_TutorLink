package com.exe201.tutorlink.main.dto.tutor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TutorSubjectSubDTO {
    private Long id;
    private Long tutorId;
    private String subjectName;
}
