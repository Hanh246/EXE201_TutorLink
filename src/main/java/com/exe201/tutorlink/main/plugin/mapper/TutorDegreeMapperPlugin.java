package com.exe201.tutorlink.main.plugin.mapper;

import com.exe201.tutorlink.common.plugin.AbstractMapperPlugin;
import com.exe201.tutorlink.main.dto.TutorDegreeDTO;
import com.exe201.tutorlink.main.entity.TutorDegrees;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TutorDegreeMapperPlugin extends AbstractMapperPlugin<TutorDegrees, TutorDegreeDTO, Long> {

    public TutorDegreeMapperPlugin(ModelMapper mapper) {
        super(TutorDegrees.class, TutorDegreeDTO.class, Long.class, mapper);
    }

    @Override
    public List<String> getSearchableFieldNames() {
        return List.of("name");
    }
}