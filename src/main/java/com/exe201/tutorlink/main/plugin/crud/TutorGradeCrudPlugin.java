package com.exe201.tutorlink.main.plugin.crud;

import com.exe201.tutorlink.common.dto.pagination.PaginationSearchDTO;
import com.exe201.tutorlink.common.plugin.AbstractCrudPlugin;
import com.exe201.tutorlink.common.plugin.IMapperPlugin;
import com.exe201.tutorlink.main.dto.tutor.TutorGradeDTO;
import com.exe201.tutorlink.main.entity.TutorGrades;
import com.exe201.tutorlink.main.entity.Tutors;
import com.exe201.tutorlink.main.repository.ITutorGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class TutorGradeCrudPlugin extends AbstractCrudPlugin<TutorGrades, TutorGradeDTO, Long, PaginationSearchDTO> {

    private final ITutorGradeRepository repository;
    @Autowired
    public TutorGradeCrudPlugin(ITutorGradeRepository repository,
                                PluginRegistry<IMapperPlugin, Class<?>> pluginRegistry) {
        super(repository, pluginRegistry, TutorGrades.class);
        this.repository = repository;
    }

    public List<TutorGradeDTO> getGradeByTutorId(Long tutorId){
        return repository.findByTutorId(tutorId).stream().map(plugin ::toDto ).toList();
    }

    public List<TutorGradeDTO> createList(List<TutorGradeDTO> degreeDTOS, Tutors tutor){
        List<TutorGrades> models = degreeDTOS.stream().map(plugin::toModel).toList();
        models.forEach(grade -> grade.setTutor(tutor));
        List<TutorGrades> savedModel = repository.saveAll(models);
        return savedModel.stream().map(plugin::toDto).toList();
    }

    public List<TutorGradeDTO> updateList(List<TutorGradeDTO> degreeDTOS, Tutors tutor) {
        repository.deleteByTutor(tutor);

        if (degreeDTOS == null || degreeDTOS.isEmpty()) {
            return Collections.emptyList();
        }

        List<TutorGrades> models = degreeDTOS.stream().map(plugin::toModel).toList();
        models.forEach(degree -> degree.setTutor(tutor));

        List<TutorGrades> savedModels = repository.saveAll(models);
        return savedModels.stream().map(plugin::toDto).toList();
    }
}
