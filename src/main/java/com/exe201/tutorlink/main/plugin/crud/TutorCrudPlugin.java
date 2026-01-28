package com.exe201.tutorlink.main.plugin.crud;

import com.exe201.tutorlink.common.dto.pagination.PaginationSearchDTO;
import com.exe201.tutorlink.common.plugin.AbstractCrudPlugin;
import com.exe201.tutorlink.common.plugin.IMapperPlugin;
import com.exe201.tutorlink.main.dto.TutorDTO;
import com.exe201.tutorlink.main.dto.TutorSearchDTO;
import com.exe201.tutorlink.main.entity.Tutors;
import com.exe201.tutorlink.main.repository.ITutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class TutorCrudPlugin extends AbstractCrudPlugin<Tutors, TutorDTO, Long, PaginationSearchDTO> {

    private final ITutorRepository repository;
    private final TutorDegreeCrudPlugin degreeCrud;
    private final TutorGradeCrudPlugin gradeCrud;
    private final TutorScheduleCrudPlugin scheduleCrud;
    private final TutorSubjectCrudPlugin subjectCrud;
    @Autowired
    public TutorCrudPlugin(ITutorRepository repository,
                           PluginRegistry<IMapperPlugin, Class<?>> pluginRegistry,
                           TutorDegreeCrudPlugin degreeCrud,
                           TutorGradeCrudPlugin gradeCrud,
                           TutorScheduleCrudPlugin scheduleCrud,
                           TutorSubjectCrudPlugin subjectCrud) {
        super(repository, pluginRegistry, Tutors.class);
        this.repository = repository;
        this.degreeCrud = degreeCrud;
        this.gradeCrud = gradeCrud;
        this.scheduleCrud = scheduleCrud;
        this.subjectCrud = subjectCrud;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TutorDTO> read(Long id) {
        return repository.findById(id)
                .map(plugin::toDto)
                .map(this::setDTO);
    }

    @Override
    @Transactional
    public TutorDTO create(TutorDTO dto) throws RuntimeException {
        Tutors model = plugin.toModel(dto);
        Tutors savedModel = repository.save(model);
        var result = plugin.toDto(savedModel);
            result.setDegrees(degreeCrud.createList(dto.getDegrees(), savedModel));
            result.setSchedules(scheduleCrud.createList(dto.getSchedules(), savedModel));
            result.setGrades(gradeCrud.createList(dto.getGrades(), savedModel));
            result.setSubjects(subjectCrud.createList(dto.getSubjects(), savedModel));
        return result;
    }

    @Override
    @Transactional
    public TutorDTO update(Long id, TutorDTO dto) throws RuntimeException {
        Tutors existingTutor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor không tồn tại"));

        plugin.updateModel(existingTutor, dto);
        Tutors updatedModel = repository.save(existingTutor);

        TutorDTO result = plugin.toDto(updatedModel);

        result.setDegrees(degreeCrud.updateList(dto.getDegrees(), updatedModel));
        result.setSchedules(scheduleCrud.updateList(dto.getSchedules(), updatedModel));
        result.setGrades(gradeCrud.updateList(dto.getGrades(), updatedModel));
        result.setSubjects(subjectCrud.updateList(dto.getSubjects(), updatedModel));

        return result;
    }

    public Page<TutorDTO> getTutorBySearch(TutorSearchDTO dto){
        var paginationDTO = dto == null ? new TutorSearchDTO() : dto;
        Pageable pageable = paginationDTO.toPageRequest();
        String search = paginationDTO.getSearch();
        String searchParam = (search != null) ? "%" + search.toLowerCase() + "%" : null;
        return repository.search(pageable, searchParam, paginationDTO.getSubjects(),
                paginationDTO.getGrades(), paginationDTO.getArea())
                .map(plugin::toDto)
                .map(this :: setDTO);
    }

    private TutorDTO setDTO(TutorDTO dto){
        dto.setDegrees(degreeCrud.getDegreeByTutorId(dto.getId()));
        dto.setGrades(gradeCrud.getGradeByTutorId(dto.getId()));
        dto.setSchedules(scheduleCrud.getScheduleByTutorId(dto.getId()));
        dto.setSubjects(subjectCrud.getSubjectByTutorId(dto.getId()));
        return dto;
    }
}
