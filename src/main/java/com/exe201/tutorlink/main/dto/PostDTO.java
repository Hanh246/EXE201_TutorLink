package com.exe201.tutorlink.main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDTO {
    private Long id;
    private String content;
    private String postType;
}
