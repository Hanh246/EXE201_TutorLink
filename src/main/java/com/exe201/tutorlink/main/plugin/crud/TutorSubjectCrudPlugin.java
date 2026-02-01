package com.exe201.tutorlink.main.plugin.crud;

import com.exe201.tutorlink.common.dto.pagination.PaginationSearchDTO;
import com.exe201.tutorlink.common.plugin.AbstractCrudPlugin;
import com.exe201.tutorlink.common.plugin.IMapperPlugin;
import com.exe201.tutorlink.main.dto.tutor.TutorSubjectDTO;
import com.exe201.tutorlink.main.dto.tutor.TutorSubjectSubDTO;
import com.exe201.tutorlink.main.entity.TutorSubjects;
import com.exe201.tutorlink.main.entity.Tutors;
import com.exe201.tutorlink.main.repository.ITutorSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class TutorSubjectCrudPlugin extends AbstractCrudPlugin<TutorSubjects, TutorSubjectDTO, Long, PaginationSearchDTO> {

    private final ITutorSubjectRepository repository;
    @Autowired
    public TutorSubjectCrudPlugin(ITutorSubjectRepository repository,
                                  PluginRegistry<IMapperPlugin, Class<?>> pluginRegistry) {
        super(repository, pluginRegistry, TutorSubjects.class);
        this.repository = repository;
    }

    public List<TutorSubjectDTO> getSubjectByTutorId(Long tutorId){
        return repository.findByTutorId(tutorId).stream().map(plugin ::toDto ).toList();
    }

    public List<TutorSubjectSubDTO> getSubjectByListTutorId(List<Long> tutorId){
        return repository.findByListTutorId(tutorId);
    }

    public List<TutorSubjectDTO> createList(List<TutorSubjectDTO> degreeDTOS, Tutors tutor){
        List<TutorSubjects> models = degreeDTOS.stream().map(plugin::toModel).toList();
        models.forEach(subject -> subject.setTutor(tutor));
        List<TutorSubjects> savedModel = repository.saveAll(models);
        return savedModel.stream().map(plugin::toDto).toList();
    }

    public List<TutorSubjectDTO> updateList(List<TutorSubjectDTO> degreeDTOS, Tutors tutor) {
        repository.deleteByTutor(tutor);

        if (degreeDTOS == null || degreeDTOS.isEmpty()) {
            return Collections.emptyList();
        }

        List<TutorSubjects> models = degreeDTOS.stream().map(plugin::toModel).toList();
        models.forEach(degree -> degree.setTutor(tutor));

        List<TutorSubjects> savedModels = repository.saveAll(models);
        return savedModels.stream().map(plugin::toDto).toList();
    }
}
