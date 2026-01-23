package com.exe201.tutorlink.main.plugin.crud;

import com.exe201.tutorlink.common.dto.pagination.PaginationSearchDTO;
import com.exe201.tutorlink.common.plugin.AbstractCrudPlugin;
import com.exe201.tutorlink.common.plugin.IMapperPlugin;
import com.exe201.tutorlink.main.dto.StudentDTO;
import com.exe201.tutorlink.main.entity.Students;
import com.exe201.tutorlink.main.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Component;

@Component
public class StudentCrudPlugin extends AbstractCrudPlugin<Students, StudentDTO, Long, PaginationSearchDTO> {

    @Autowired
public StudentCrudPlugin(IStudentRepository repository,
                         PluginRegistry<IMapperPlugin, Class<?>> pluginRegistry) {
        super(repository, pluginRegistry, Students.class);
    }
}
