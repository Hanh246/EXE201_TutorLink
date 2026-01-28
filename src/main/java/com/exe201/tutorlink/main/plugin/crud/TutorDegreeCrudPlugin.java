package com.exe201.tutorlink.main.plugin.crud;

import com.exe201.tutorlink.common.dto.pagination.PaginationSearchDTO;
import com.exe201.tutorlink.common.plugin.AbstractCrudPlugin;
import com.exe201.tutorlink.common.plugin.IMapperPlugin;
import com.exe201.tutorlink.main.dto.TutorDegreeDTO;
import com.exe201.tutorlink.main.entity.TutorDegrees;
import com.exe201.tutorlink.main.entity.Tutors;
import com.exe201.tutorlink.main.repository.ITutorDegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class TutorDegreeCrudPlugin extends AbstractCrudPlugin<TutorDegrees, TutorDegreeDTO, Long, PaginationSearchDTO> {

    private final ITutorDegreeRepository repository;
    @Autowired
    public TutorDegreeCrudPlugin(ITutorDegreeRepository repository,
                                 PluginRegistry<IMapperPlugin, Class<?>> pluginRegistry) {
        super(repository, pluginRegistry, TutorDegrees.class);
        this.repository = repository;
    }

    public List<TutorDegreeDTO> getDegreeByTutorId(Long tutorId){
        return repository.findByTutorId(tutorId).stream().map(plugin ::toDto ).toList();
    }

    public List<TutorDegreeDTO> createList(List<TutorDegreeDTO> degreeDTOS, Tutors tutor){
        List<TutorDegrees> models = degreeDTOS.stream().map(plugin::toModel).toList();
        models.forEach(degree -> degree.setTutor(tutor));
        List<TutorDegrees> savedModel = repository.saveAll(models);
        return savedModel.stream().map(plugin::toDto).toList();
    }

    public List<TutorDegreeDTO> updateList(List<TutorDegreeDTO> degreeDTOS, Tutors tutor) {
        repository.deleteByTutor(tutor);

        if (degreeDTOS == null || degreeDTOS.isEmpty()) {
            return Collections.emptyList();
        }

        List<TutorDegrees> models = degreeDTOS.stream().map(plugin::toModel).toList();
        models.forEach(degree -> degree.setTutor(tutor));

        List<TutorDegrees> savedModels = repository.saveAll(models);
        return savedModels.stream().map(plugin::toDto).toList();
    }
}
