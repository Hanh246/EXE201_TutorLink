package com.exe201.tutorlink.main.repository;

import com.exe201.tutorlink.common.repository.AbstractCrudRepository;
import com.exe201.tutorlink.main.entity.Students;
import com.exe201.tutorlink.main.entity.Subjects;
import org.springframework.stereotype.Repository;

@Repository
public interface ISubjectRepository extends AbstractCrudRepository<Subjects, Long> {
}
