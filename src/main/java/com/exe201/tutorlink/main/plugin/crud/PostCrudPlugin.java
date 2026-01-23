package com.exe201.tutorlink.main.plugin.crud;

import com.exe201.tutorlink.common.dto.pagination.PaginationSearchDTO;
import com.exe201.tutorlink.common.plugin.AbstractCrudPlugin;
import com.exe201.tutorlink.common.plugin.IMapperPlugin;
import com.exe201.tutorlink.main.dto.PostDTO;
import com.exe201.tutorlink.main.entity.Posts;
import com.exe201.tutorlink.main.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;

@Component
public class PostCrudPlugin extends AbstractCrudPlugin<Posts, PostDTO, Long, PaginationSearchDTO> {

    @Autowired
public PostCrudPlugin(IPostRepository repository,
                      PluginRegistry<IMapperPlugin, Class<?>> pluginRegistry) {
        super(repository, pluginRegistry, Posts.class);
    }
}
