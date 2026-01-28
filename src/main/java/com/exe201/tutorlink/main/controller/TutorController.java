package com.exe201.tutorlink.main.controller;

import com.exe201.tutorlink.common.controller.BaseController;
import com.exe201.tutorlink.common.dto.pagination.PaginationMetadata;
import com.exe201.tutorlink.common.dto.pagination.PaginationSearchDTO;
import com.exe201.tutorlink.common.dto.response.PaginationResponse;
import com.exe201.tutorlink.main.dto.TutorDTO;
import com.exe201.tutorlink.main.dto.TutorSearchDTO;
import com.exe201.tutorlink.main.entity.Tutors;
import com.exe201.tutorlink.main.plugin.crud.TutorCrudPlugin;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tutor")
public class TutorController extends BaseController<Tutors, TutorDTO, Long, PaginationSearchDTO> {

    private final TutorCrudPlugin crudPlugin;
    public TutorController(TutorCrudPlugin crudPlugin) {
        super(crudPlugin, Tutors.class);
        this.crudPlugin = crudPlugin;
    }

    @GetMapping("/search")
    public ResponseEntity<PaginationResponse<List<TutorDTO>>> search(@Valid @ParameterObject TutorSearchDTO paginationDTO) {
        var data = crudPlugin.getTutorBySearch(paginationDTO);
        return ResponseEntity
                .ok(PaginationResponse.<List<TutorDTO>>builder()
                        .metadata(
                                new PaginationMetadata(
                                        paginationDTO.getPage(),
                                        paginationDTO.getSize(),
                                        data.getTotalElements(),
                                        data.getTotalPages()
                                )
                        )
                        .success(true)
                        .data(data.toList())
                        .build());
    }
}
