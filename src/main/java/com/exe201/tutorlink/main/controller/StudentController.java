package com.exe201.tutorlink.main.controller;

import com.exe201.tutorlink.common.controller.BaseController;
import com.exe201.tutorlink.common.dto.pagination.PaginationSearchDTO;
import com.exe201.tutorlink.main.dto.StudentDTO;
import com.exe201.tutorlink.main.entity.Students;
import com.exe201.tutorlink.main.plugin.crud.StudentCrudPlugin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController extends BaseController<Students, StudentDTO, Long, PaginationSearchDTO> {

    public StudentController(StudentCrudPlugin crudPlugin) {
        super(crudPlugin, Students.class);
    }
}
