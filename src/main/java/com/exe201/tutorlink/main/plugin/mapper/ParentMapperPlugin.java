package com.exe201.tutorlink.main.plugin.mapper;

import com.exe201.tutorlink.common.plugin.AbstractMapperPlugin;
import com.exe201.tutorlink.main.dto.ParentDTO;
import com.exe201.tutorlink.main.entity.Parents;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParentMapperPlugin extends AbstractMapperPlugin<Parents, ParentDTO, Long> {

    public ParentMapperPlugin(ModelMapper mapper) {
        super(Parents.class, ParentDTO.class, Long.class, mapper);
    }
    @Override
    public List<String> getSearchableFieldNames() {
        return List.of("name");
    }

}
