package com.exe201.tutorlink.main.repository;

import com.exe201.tutorlink.common.repository.AbstractCrudRepository;
import com.exe201.tutorlink.main.entity.Tutors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITutorRepository extends AbstractCrudRepository<Tutors, Long> {
    @Query("""
            SELECT t
            FROM Tutors t
            LEFT JOIN TutorSubjects s ON s.tutor = t
            LEFT JOIN TutorGrades g ON g.tutor = t
            WHERE (:search IS NULL OR LOWER(t.name) LIKE CONCAT('%', :search, '%'))
                  AND (:area IS NULL OR t.location = :area)
                  AND (:subjects IS NULL OR s.subjectName IN :subjects)
                  AND (:grades IS NULL OR g.grade IN :grades)
                  AND t.deleted = false""")
    Page<Tutors> search(Pageable pageable, String search, List<String> subjects, List<String> grades, String area);
}
