package com.exe201.tutorlink.main.plugin.mapper;

import com.exe201.tutorlink.common.plugin.AbstractMapperPlugin;
import com.exe201.tutorlink.main.dto.UserDTO;
import com.exe201.tutorlink.main.entity.Users;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapperPlugin extends AbstractMapperPlugin<Users, UserDTO, Long> {

    public UserMapperPlugin(ModelMapper mapper) {
        super(Users.class, UserDTO.class, Long.class, mapper);
    }
    @Override
    public List<String> getSearchableFieldNames() {
        return List.of("name");
    }

}
