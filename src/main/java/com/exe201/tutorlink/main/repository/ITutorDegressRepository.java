package com.exe201.tutorlink.main.repository;

import com.exe201.tutorlink.common.repository.AbstractCrudRepository;
import com.exe201.tutorlink.main.entity.TutorDegrees;
import com.exe201.tutorlink.main.entity.Tutors;
import org.springframework.stereotype.Repository;

@Repository
public interface ITutorDegressRepository extends AbstractCrudRepository<TutorDegrees, Long> {
}
