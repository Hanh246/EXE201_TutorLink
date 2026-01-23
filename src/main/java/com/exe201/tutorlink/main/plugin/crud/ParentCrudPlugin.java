package com.exe201.tutorlink.main.plugin.crud;

import com.exe201.tutorlink.common.dto.pagination.PaginationSearchDTO;
import com.exe201.tutorlink.common.plugin.AbstractCrudPlugin;
import com.exe201.tutorlink.common.plugin.IMapperPlugin;
import com.exe201.tutorlink.main.dto.ParentDTO;
import com.exe201.tutorlink.main.entity.Parents;
import com.exe201.tutorlink.main.repository.IParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;

@Component
public class ParentCrudPlugin extends AbstractCrudPlugin<Parents, ParentDTO, Long, PaginationSearchDTO> {

    @Autowired
public ParentCrudPlugin(IParentRepository repository,
                        PluginRegistry<IMapperPlugin, Class<?>> pluginRegistry) {
        super(repository, pluginRegistry, Parents.class);
    }
}
