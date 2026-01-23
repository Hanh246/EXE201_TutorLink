package com.exe201.tutorlink.main.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TutorScheduleDTO {
    private String dayOfWeek;
    private String startTime;
    private String endTime;
    private String shift;
}
