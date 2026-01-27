package com.exe201.tutorlink.main.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ParentDTO extends UserBaseDTO{
    private List<StudentDTO> child;

    private String address;
}
