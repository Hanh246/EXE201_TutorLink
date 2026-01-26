package com.exe201.tutorlink.main.plugin.mapper;

import com.exe201.tutorlink.common.plugin.AbstractMapperPlugin;
import com.exe201.tutorlink.main.dto.TutorSubjectDTO;
import com.exe201.tutorlink.main.dto.TutorDTO;
import com.exe201.tutorlink.main.dto.TutorGradeDTO;
import com.exe201.tutorlink.main.dto.TutorScheduleDTO;
import com.exe201.tutorlink.main.entity.TutorGrades;
import com.exe201.tutorlink.main.entity.TutorSchedules;
import com.exe201.tutorlink.main.entity.TutorSubjects;
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
    @Override
    protected void configureModelMapper(){
        super.configureModelMapper();
        modelMapper.typeMap(TutorSubjects.class, TutorSubjectDTO.class);
        modelMapper.typeMap(TutorSchedules.class, TutorScheduleDTO.class);
        modelMapper.typeMap(TutorGrades.class, TutorGradeDTO.class);
    }

    @Override
    protected void performCustomUpdate(Tutors existingModel, TutorDTO dto) {
        if (existingModel.getSubjects() != null) {
            existingModel.getSubjects().forEach(s -> s.setTutor(existingModel));
        }

        if (existingModel.getSchedules() != null) {
            existingModel.getSchedules().forEach(s -> s.setTutor(existingModel));
        }

        if (existingModel.getGrades() != null) {
            existingModel.getGrades().forEach(g -> g.setTutor(existingModel));
        }

        if (existingModel.getDegrees() != null) {
            existingModel.getDegrees().forEach(g -> g.setTutor(existingModel));
        }
    }
}
