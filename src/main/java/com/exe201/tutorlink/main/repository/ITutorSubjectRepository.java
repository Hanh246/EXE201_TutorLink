package com.exe201.tutorlink.main.repository;

import com.exe201.tutorlink.common.repository.AbstractCrudRepository;
import com.exe201.tutorlink.main.dto.tutor.TutorSubjectSubDTO;
import com.exe201.tutorlink.main.entity.TutorSubjects;
import com.exe201.tutorlink.main.entity.Tutors;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ITutorSubjectRepository extends AbstractCrudRepository<TutorSubjects, Long> {

    @Query("""
            SELECT t
            FROM TutorSubjects t
            WHERE t.tutor.id = :tutorId
            AND t.deleted = false
            """)
    List<TutorSubjects> findByTutorId(Long tutorId);

    @Query("""
            SELECT new com.exe201.tutorlink.main.dto.tutor.TutorSubjectSubDTO(
                    s.id,
                    t.id,
                    s.subjectName
                )
            FROM TutorSubjects s
            LEFT JOIN s.tutor t
            WHERE s.tutor.id IN :tutorId
            AND s.deleted = false
            """)
    List<TutorSubjectSubDTO> findByListTutorId(List<Long> tutorId);

    @Modifying
    @Transactional
    @Query("DELETE FROM TutorSubjects d WHERE d.tutor = :tutor")
    void deleteByTutor(@Param("tutor") Tutors tutor);
}
