package com.exe201.tutorlink.main.repository;

import com.exe201.tutorlink.common.repository.AbstractCrudRepository;
import com.exe201.tutorlink.main.entity.TutorSubjects;
import org.springframework.stereotype.Repository;

@Repository
public interface ITutorSubjectRepository extends AbstractCrudRepository<TutorSubjects, Long> {
}
