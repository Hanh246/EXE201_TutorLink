package com.exe201.tutorlink.main.plugin.mapper;

import com.exe201.tutorlink.common.plugin.AbstractMapperPlugin;
import com.exe201.tutorlink.main.dto.TutorDTO;
import com.exe201.tutorlink.main.entity.Tutors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TutorMapperPlugin extends AbstractMapperPlugin<Tutors, TutorDTO, Long> {

    public TutorMapperPlugin(ModelMapper mapper) {
        super(Tutors.class, TutorDTO.class, Long.class, mapper);
    }
    @Override
    public List<String> getSearchableFieldNames() {
        return List.of("name");
    }

}
