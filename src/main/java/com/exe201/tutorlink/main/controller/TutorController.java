package com.exe201.tutorlink.main.controller;

import com.exe201.tutorlink.common.controller.BaseController;
import com.exe201.tutorlink.common.dto.pagination.PaginationSearchDTO;
import com.exe201.tutorlink.main.dto.TutorDTO;
import com.exe201.tutorlink.main.entity.Tutors;
import com.exe201.tutorlink.main.plugin.crud.TutorCrudPlugin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tutor")
public class TutorController extends BaseController<Tutors, TutorDTO, Long, PaginationSearchDTO> {

    public TutorController(TutorCrudPlugin crudPlugin) {
        super(crudPlugin, Tutors.class);
    }
}
