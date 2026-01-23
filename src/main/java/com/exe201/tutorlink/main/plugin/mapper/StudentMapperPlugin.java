package com.exe201.tutorlink.main.plugin.mapper;

import com.exe201.tutorlink.common.plugin.AbstractMapperPlugin;
import com.exe201.tutorlink.main.dto.StudentDTO;
import com.exe201.tutorlink.main.entity.Students;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentMapperPlugin extends AbstractMapperPlugin<Students, StudentDTO, Long> {

    public StudentMapperPlugin(ModelMapper mapper) {
        super(Students.class, StudentDTO.class, Long.class, mapper);
    }
    @Override
    public List<String> getSearchableFieldNames() {
        return List.of("name");
    }

}
