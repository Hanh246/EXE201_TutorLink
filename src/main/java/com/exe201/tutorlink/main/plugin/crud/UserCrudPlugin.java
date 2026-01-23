package com.exe201.tutorlink.main.plugin.crud;

import com.exe201.tutorlink.common.dto.pagination.PaginationSearchDTO;
import com.exe201.tutorlink.common.plugin.AbstractCrudPlugin;
import com.exe201.tutorlink.common.plugin.IMapperPlugin;
import com.exe201.tutorlink.main.dto.UserDTO;
import com.exe201.tutorlink.main.entity.Users;
import com.exe201.tutorlink.main.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;

@Component
public class UserCrudPlugin extends AbstractCrudPlugin<Users, UserDTO, Long, PaginationSearchDTO> {

    @Autowired
public UserCrudPlugin(IUserRepository repository,
                      PluginRegistry<IMapperPlugin, Class<?>> pluginRegistry) {
        super(repository, pluginRegistry, Users.class);
    }
}
