package com.exe201.tutorlink.main.controller;

import com.exe201.tutorlink.common.controller.BaseController;
import com.exe201.tutorlink.common.dto.pagination.PaginationSearchDTO;
import com.exe201.tutorlink.main.dto.ParentDTO;
import com.exe201.tutorlink.main.entity.Parents;
import com.exe201.tutorlink.main.plugin.crud.ParentCrudPlugin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parent")
public class ParentController extends BaseController<Parents, ParentDTO, Long, PaginationSearchDTO> {

    public ParentController(ParentCrudPlugin crudPlugin) {
        super(crudPlugin, Parents.class);
    }
}
