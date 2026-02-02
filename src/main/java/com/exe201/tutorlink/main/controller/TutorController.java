package com.exe201.tutorlink.main.controller;

import com.exe201.tutorlink.common.controller.BaseController;
import com.exe201.tutorlink.common.dto.pagination.PaginationMetadata;
import com.exe201.tutorlink.common.dto.pagination.PaginationSearchDTO;
import com.exe201.tutorlink.common.dto.request.IBaseUpdateRequest;
import com.exe201.tutorlink.common.dto.response.BaseResponse;
import com.exe201.tutorlink.common.dto.response.PaginationResponse;
import com.exe201.tutorlink.main.dto.tutor.TutorDTO;
import com.exe201.tutorlink.main.dto.tutor.TutorSearchDTO;
import com.exe201.tutorlink.main.dto.tutor.TutorSearchFilterDTO;
import com.exe201.tutorlink.main.entity.Tutors;
import com.exe201.tutorlink.main.plugin.crud.TutorCrudPlugin;
import com.exe201.tutorlink.main.service.CloudinaryService;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/tutor")
public class TutorController extends BaseController<Tutors, TutorDTO, Long, PaginationSearchDTO> {

    private final TutorCrudPlugin crudPlugin;
    private final CloudinaryService cloudinaryService;
    public TutorController(TutorCrudPlugin crudPlugin,
                           CloudinaryService cloudinaryService) {
        super(crudPlugin, Tutors.class);
        this.crudPlugin = crudPlugin;
        this.cloudinaryService = cloudinaryService;
    }


    @Override
    @PutMapping("/{id}")
    @Hidden
    public ResponseEntity<BaseResponse<TutorDTO>> update(@PathVariable Long id, @RequestBody TutorDTO dto) {
        return  null;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BaseResponse<TutorDTO>> updateFile(@PathVariable Long id,
                                                             @RequestPart("dto") TutorDTO dto,
                                                             @RequestPart(value = "file", required = false) List<MultipartFile> files) {
        if (dto instanceof IBaseUpdateRequest) {
            ((IBaseUpdateRequest<Long>) dto).setId(id);
        }
        List<String> url = cloudinaryService.uploadListImages(files);
        TutorDTO updated = crudPlugin.update(id, dto, url);
        return ResponseEntity.ok(BaseResponse.<TutorDTO>builder()
                .success(true)
                .data(updated)
                .build());
    }

    @GetMapping("/search")
    public ResponseEntity<PaginationResponse<List<TutorSearchDTO>>> search(@Valid @ParameterObject TutorSearchFilterDTO paginationDTO) {
        var data = crudPlugin.getTutorBySearch(paginationDTO);
        return ResponseEntity
                .ok(PaginationResponse.<List<TutorSearchDTO>>builder()
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
