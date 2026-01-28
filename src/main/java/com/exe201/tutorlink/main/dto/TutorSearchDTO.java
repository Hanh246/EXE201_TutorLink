package com.exe201.tutorlink.main.dto;

import com.exe201.tutorlink.common.dto.pagination.PaginationSearchDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TutorSearchDTO extends PaginationSearchDTO {
    private List<String> subjects;
    private List<String> grades;
    private String area;
}
