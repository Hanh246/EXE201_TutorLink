package com.exe201.tutorlink.main.controller;

import com.exe201.tutorlink.common.controller.BaseController;
import com.exe201.tutorlink.common.dto.pagination.PaginationSearchDTO;
import com.exe201.tutorlink.main.dto.UserDTO;
import com.exe201.tutorlink.main.entity.Users;
import com.exe201.tutorlink.main.plugin.crud.UserCrudPlugin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController<Users, UserDTO, Long, PaginationSearchDTO> {

    public UserController(UserCrudPlugin crudPlugin) {
        super(crudPlugin, Users.class);
    }
}
