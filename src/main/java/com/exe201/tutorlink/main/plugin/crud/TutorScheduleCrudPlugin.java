package com.exe201.tutorlink.main.plugin.crud;

import com.exe201.tutorlink.common.dto.pagination.PaginationSearchDTO;
import com.exe201.tutorlink.common.plugin.AbstractCrudPlugin;
import com.exe201.tutorlink.common.plugin.IMapperPlugin;
import com.exe201.tutorlink.main.dto.TutorScheduleDTO;
import com.exe201.tutorlink.main.entity.TutorSchedules;
import com.exe201.tutorlink.main.entity.Tutors;
import com.exe201.tutorlink.main.repository.ITutorScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class TutorScheduleCrudPlugin extends AbstractCrudPlugin<TutorSchedules, TutorScheduleDTO, Long, PaginationSearchDTO> {

    private final ITutorScheduleRepository repository;
    @Autowired
    public TutorScheduleCrudPlugin(ITutorScheduleRepository repository,
                                   PluginRegistry<IMapperPlugin, Class<?>> pluginRegistry) {
        super(repository, pluginRegistry, TutorSchedules.class);
        this.repository = repository;
    }

    public List<TutorScheduleDTO> getScheduleByTutorId(Long tutorId){
        return repository.findByTutorId(tutorId).stream().map(plugin ::toDto ).toList();
    }

    public List<TutorScheduleDTO> createList(List<TutorScheduleDTO> degreeDTOS, Tutors tutor){
        List<TutorSchedules> models = degreeDTOS.stream().map(plugin::toModel).toList();
        models.forEach(schedule -> schedule.setTutor(tutor));
        List<TutorSchedules> savedModel = repository.saveAll(models);
        return savedModel.stream().map(plugin::toDto).toList();
    }

    public List<TutorScheduleDTO> updateList(List<TutorScheduleDTO> degreeDTOS, Tutors tutor) {
        repository.deleteByTutor(tutor);

        if (degreeDTOS == null || degreeDTOS.isEmpty()) {
            return Collections.emptyList();
        }

        List<TutorSchedules> models = degreeDTOS.stream().map(plugin::toModel).toList();
        models.forEach(degree -> degree.setTutor(tutor));

        List<TutorSchedules> savedModels = repository.saveAll(models);
        return savedModels.stream().map(plugin::toDto).toList();
    }
}
