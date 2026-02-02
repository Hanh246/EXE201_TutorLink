package com.exe201.tutorlink.main.plugin.crud;

import com.exe201.tutorlink.common.dto.pagination.PaginationSearchDTO;
import com.exe201.tutorlink.common.plugin.AbstractCrudPlugin;
import com.exe201.tutorlink.common.plugin.IMapperPlugin;
import com.exe201.tutorlink.main.constants.TutorUpdateStatusEnum;
import com.exe201.tutorlink.main.dto.tutor.*;
import com.exe201.tutorlink.main.entity.Tutors;
import com.exe201.tutorlink.main.repository.ITutorRepository;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TutorCrudPlugin extends AbstractCrudPlugin<Tutors, TutorDTO, Long, PaginationSearchDTO> {

    private final ITutorRepository repository;
    private final TutorDegreeCrudPlugin degreeCrud;
    private final TutorGradeCrudPlugin gradeCrud;
    private final TutorScheduleCrudPlugin scheduleCrud;
    private final TutorSubjectCrudPlugin subjectCrud;
    private final ModelMapper mapper;
    @Autowired
    public TutorCrudPlugin(ITutorRepository repository,
                           PluginRegistry<IMapperPlugin, Class<?>> pluginRegistry,
                           TutorDegreeCrudPlugin degreeCrud,
                           TutorGradeCrudPlugin gradeCrud,
                           TutorScheduleCrudPlugin scheduleCrud,
                           TutorSubjectCrudPlugin subjectCrud,
                           ModelMapper mapper) {
        super(repository, pluginRegistry, Tutors.class);
        this.repository = repository;
        this.degreeCrud = degreeCrud;
        this.gradeCrud = gradeCrud;
        this.scheduleCrud = scheduleCrud;
        this.subjectCrud = subjectCrud;
        this.mapper = mapper;
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
        model.setUpdateStatus(TutorUpdateStatusEnum.FIRST);
        Tutors savedModel = repository.save(model);
        var result = plugin.toDto(savedModel);
            result.setDegrees(degreeCrud.createList(dto.getDegrees(), savedModel));
            result.setSchedules(scheduleCrud.createList(dto.getSchedules(), savedModel));
            result.setGrades(gradeCrud.createList(dto.getGrades(), savedModel));
            result.setSubjects(subjectCrud.createList(dto.getSubjects(), savedModel));
        return result;
    }

    @Transactional
    public TutorDTO update(Long id, TutorDTO dto, List<String> url) throws RuntimeException {
        Tutors existingTutor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tutor không tồn tại"));
        if (url != null && !url.isEmpty()){
            List<TutorDegreeDTO> newDegreeDTOs = url.stream().map(link -> {
                TutorDegreeDTO degree = new TutorDegreeDTO();
                degree.setDegreeImageUrl(link);
                return degree;
            }).collect(Collectors.toList());

            dto.getDegrees().addAll(newDegreeDTOs);
        }
        plugin.updateModel(existingTutor, dto);
        existingTutor.setUpdateStatus(updateStatus(dto));
        Tutors updatedModel = repository.save(existingTutor);

        TutorDTO result = plugin.toDto(updatedModel);

        result.setDegrees(degreeCrud.updateList(dto.getDegrees(), updatedModel));
        result.setSchedules(scheduleCrud.updateList(dto.getSchedules(), updatedModel));
        result.setGrades(gradeCrud.updateList(dto.getGrades(), updatedModel));
        result.setSubjects(subjectCrud.updateList(dto.getSubjects(), updatedModel));

        return result;
    }

    public Page<TutorSearchDTO> getTutorBySearch(TutorSearchFilterDTO dto){
        TutorSearchFilterDTO filter = dto != null ? dto : new TutorSearchFilterDTO();
        Pageable pageable = filter.toPageRequest();

        String searchParam = Optional.ofNullable(filter.getSearch())
                .filter(s -> !s.isBlank())
                .map(s -> "%" + s.toLowerCase() + "%")
                .orElse(null);

        Page<Tutors> tutorPage = repository.search(
                pageable,
                searchParam,
                filter.getSubjects(),
                filter.getGrades(),
                filter.getArea()
        );

        List<Long> tutorIds = tutorPage.getContent()
                .stream()
                .map(Tutors::getId)
                .toList();

        Map<Long, List<TutorSubjectDTO>> subjectMap = getSubjectsByTutorIds(tutorIds);

        return tutorPage.map(tutor -> mapToSearchDTO(tutor, subjectMap));
    }

    private TutorDTO setDTO(TutorDTO dto){
        dto.setDegrees(degreeCrud.getDegreeByTutorId(dto.getId()));
        dto.setGrades(gradeCrud.getGradeByTutorId(dto.getId()));
        dto.setSchedules(scheduleCrud.getScheduleByTutorId(dto.getId()));
        dto.setSubjects(subjectCrud.getSubjectByTutorId(dto.getId()));
        return dto;
    }

    private TutorSearchDTO mapToSearchDTO(Tutors tutor, Map<Long, List<TutorSubjectDTO>> subjectMap){
        TutorSearchDTO dto = mapper.map(tutor, TutorSearchDTO.class);
        dto.setSubjects(subjectMap.getOrDefault(tutor.getId(), List.of()));
        return dto;
    }

    public Map<Long, List<TutorSubjectDTO>> getSubjectsByTutorIds(List<Long> tutorIds) {

        List<TutorSubjectSubDTO> rows = subjectCrud.getSubjectByListTutorId(tutorIds);

        return rows.stream()
                .collect(Collectors.groupingBy(
                        TutorSubjectSubDTO::getTutorId,
                        Collectors.mapping(
                                subDto -> mapper.map(subDto, TutorSubjectDTO.class),
                                Collectors.toList())
                ));
    }
    private TutorUpdateStatusEnum updateStatus (TutorDTO dto){
        if (hasSubjects(dto)) {
            return TutorUpdateStatusEnum.PAGE_4;
        }
        if (StringUtils.isNotBlank(dto.getSchoolName())) {
            return TutorUpdateStatusEnum.PAGE_3;
        }
        if (StringUtils.isNotBlank(dto.getSoCCCD())) {
            return TutorUpdateStatusEnum.PAGE_2;
        }
        if (StringUtils.isNotBlank(dto.getName())) {
            return TutorUpdateStatusEnum.PAGE_1;
        }
        return TutorUpdateStatusEnum.FIRST;
    }

    private boolean hasSubjects(TutorDTO dto) {
        return dto.getSubjects() != null && !dto.getSubjects().isEmpty();
    }
}
