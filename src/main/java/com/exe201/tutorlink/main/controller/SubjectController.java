package com.exe201.tutorlink.main.controller;

import com.exe201.tutorlink.common.controller.BaseController;
import com.exe201.tutorlink.common.dto.pagination.PaginationSearchDTO;
import com.exe201.tutorlink.main.dto.SubjectDTO;
import com.exe201.tutorlink.main.entity.Subjects;
import com.exe201.tutorlink.main.plugin.crud.SubjectCrudPlugin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subject")
public class SubjectController extends BaseController<Subjects, SubjectDTO, Long, PaginationSearchDTO> {

    public SubjectController(SubjectCrudPlugin crudPlugin) {
        super(crudPlugin, Subjects.class);
    }
}
