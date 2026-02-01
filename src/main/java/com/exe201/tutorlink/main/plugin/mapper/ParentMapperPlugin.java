package com.exe201.tutorlink.main.plugin.mapper;

import com.exe201.tutorlink.common.plugin.AbstractMapperPlugin;
import com.exe201.tutorlink.main.dto.*;
import com.exe201.tutorlink.main.dto.tutor.TutorGradeDTO;
import com.exe201.tutorlink.main.dto.tutor.TutorScheduleDTO;
import com.exe201.tutorlink.main.entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParentMapperPlugin extends AbstractMapperPlugin<Parents, ParentDTO, Long> {

    public ParentMapperPlugin(ModelMapper mapper) {
        super(Parents.class, ParentDTO.class, Long.class, mapper);
    }
    @Override
    public List<String> getSearchableFieldNames() {
        return List.of("name");
    }

    @Override
    protected void configureModelMapper(){
        super.configureModelMapper();
        modelMapper.typeMap(Students.class, StudentDTO.class);
        modelMapper.typeMap(TutorSchedules.class, TutorScheduleDTO.class);
        modelMapper.typeMap(TutorGrades.class, TutorGradeDTO.class);
    }

    @Override
    protected void performCustomUpdate(Parents existingModel, ParentDTO dto) {
        if (existingModel.getChild() != null) {
            existingModel.getChild().forEach(s -> s.setParent(existingModel));
        }
    }
}
