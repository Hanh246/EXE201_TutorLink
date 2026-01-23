package com.exe201.tutorlink.main.repository;

import com.exe201.tutorlink.common.repository.AbstractCrudRepository;
import com.exe201.tutorlink.main.entity.TutorGrades;
import com.exe201.tutorlink.main.entity.Tutors;
import org.springframework.stereotype.Repository;

@Repository
public interface ITutorGradesRepository extends AbstractCrudRepository<TutorGrades, Long> {
}
