package com.exe201.tutorlink.main.repository;

import com.exe201.tutorlink.common.repository.AbstractCrudRepository;
import com.exe201.tutorlink.main.entity.Students;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends AbstractCrudRepository<Students, Long> {
}
