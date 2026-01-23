package com.exe201.tutorlink.main.plugin.mapper;

import com.exe201.tutorlink.common.plugin.AbstractMapperPlugin;
import com.exe201.tutorlink.main.dto.PostDTO;
import com.exe201.tutorlink.main.entity.Posts;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostMapperPlugin extends AbstractMapperPlugin<Posts, PostDTO, Long> {

    public PostMapperPlugin(ModelMapper mapper) {
        super(Posts.class, PostDTO.class, Long.class, mapper);
    }
    @Override
    public List<String> getSearchableFieldNames() {
        return List.of("content");
    }

}
