package com.exe201.tutorlink.main.repository;

import com.exe201.tutorlink.common.repository.AbstractCrudRepository;
import com.exe201.tutorlink.main.entity.Subjects;
import com.exe201.tutorlink.main.entity.Tutors;
import org.springframework.stereotype.Repository;

@Repository
public interface ITutorRepository extends AbstractCrudRepository<Tutors, Long> {
}
