package com.exe201.tutorlink.main.plugin.crud;

import com.exe201.tutorlink.common.dto.pagination.PaginationSearchDTO;
import com.exe201.tutorlink.common.plugin.AbstractCrudPlugin;
import com.exe201.tutorlink.common.plugin.IMapperPlugin;
import com.exe201.tutorlink.main.dto.SubjectDTO;
import com.exe201.tutorlink.main.entity.Subjects;
import com.exe201.tutorlink.main.repository.ISubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;

@Component
public class SubjectCrudPlugin extends AbstractCrudPlugin<Subjects, SubjectDTO, Long, PaginationSearchDTO> {

    @Autowired
public SubjectCrudPlugin(ISubjectRepository repository,
                         PluginRegistry<IMapperPlugin, Class<?>> pluginRegistry) {
        super(repository, pluginRegistry, Subjects.class);
    }
}
