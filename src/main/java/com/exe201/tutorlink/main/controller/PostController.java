package com.exe201.tutorlink.main.controller;

import com.exe201.tutorlink.common.controller.BaseController;
import com.exe201.tutorlink.common.dto.pagination.PaginationSearchDTO;
import com.exe201.tutorlink.main.dto.PostDTO;
import com.exe201.tutorlink.main.entity.Posts;
import com.exe201.tutorlink.main.plugin.crud.PostCrudPlugin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController extends BaseController<Posts, PostDTO, Long, PaginationSearchDTO> {

    public PostController(PostCrudPlugin crudPlugin) {
        super(crudPlugin, Posts.class);
    }
}
