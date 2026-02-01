package com.exe201.tutorlink.main.plugin.mapper;

import com.exe201.tutorlink.common.plugin.AbstractMapperPlugin;
import com.exe201.tutorlink.main.dto.tutor.TutorScheduleDTO;
import com.exe201.tutorlink.main.entity.TutorSchedules;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TutorScheduleMapperPlugin extends AbstractMapperPlugin<TutorSchedules, TutorScheduleDTO, Long> {

    public TutorScheduleMapperPlugin(ModelMapper mapper) {
        super(TutorSchedules.class, TutorScheduleDTO.class, Long.class, mapper);
    }

    @Override
    public List<String> getSearchableFieldNames() {
        return List.of("dayOfWeek");
    }
}