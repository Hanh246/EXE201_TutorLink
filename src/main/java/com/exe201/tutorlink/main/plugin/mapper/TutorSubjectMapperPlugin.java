package com.exe201.tutorlink.main.plugin.mapper;

import com.exe201.tutorlink.common.plugin.AbstractMapperPlugin;
import com.exe201.tutorlink.main.dto.tutor.TutorSubjectDTO;
import com.exe201.tutorlink.main.entity.TutorSubjects;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TutorSubjectMapperPlugin extends AbstractMapperPlugin<TutorSubjects, TutorSubjectDTO, Long> {

    public TutorSubjectMapperPlugin(ModelMapper mapper) {
        super(TutorSubjects.class, TutorSubjectDTO.class, Long.class, mapper);
    }

    @Override
    public List<String> getSearchableFieldNames() {
        return List.of("subjectName");
    }
}