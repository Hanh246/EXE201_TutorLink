package com.exe201.tutorlink.main.plugin.mapper;

import com.exe201.tutorlink.common.plugin.AbstractMapperPlugin;
import com.exe201.tutorlink.main.dto.tutor.TutorGradeDTO;
import com.exe201.tutorlink.main.entity.TutorGrades;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TutorGradeMapperPlugin extends AbstractMapperPlugin<TutorGrades, TutorGradeDTO, Long> {

    public TutorGradeMapperPlugin(ModelMapper mapper) {
        super(TutorGrades.class, TutorGradeDTO.class, Long.class, mapper);
    }

    @Override
    public List<String> getSearchableFieldNames() {
        return List.of("grade");
    }
}