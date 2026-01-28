package com.exe201.tutorlink.main.repository;

import com.exe201.tutorlink.common.repository.AbstractCrudRepository;
import com.exe201.tutorlink.main.entity.TutorSchedules;
import com.exe201.tutorlink.main.entity.Tutors;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ITutorScheduleRepository extends AbstractCrudRepository<TutorSchedules, Long> {

    @Query("""
            SELECT t
            FROM TutorSchedules t
            WHERE t.tutor.id = :tutorId
            AND t.deleted = false
            """)
    List<TutorSchedules> findByTutorId(Long tutorId);

    @Modifying
    @Transactional
    @Query("DELETE FROM TutorSchedules d WHERE d.tutor = :tutor")
    void deleteByTutor(@Param("tutor") Tutors tutor);
}
