package com.exe201.tutorlink.main.dto;

import com.exe201.tutorlink.main.entity.Students;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ParentDTO {
    private Long id;

    private String name;

    private List<Students> child;

    private String gender;

    private String address;
}
