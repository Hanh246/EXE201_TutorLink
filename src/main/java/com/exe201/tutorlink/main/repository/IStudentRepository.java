package com.exe201.tutorlink.main.repository;

import com.exe201.tutorlink.common.repository.AbstractCrudRepository;
import com.exe201.tutorlink.main.entity.Students;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IStudentRepository extends AbstractCrudRepository<Students, Long> {

    @Query("""
            SELECT s
            FROM Students s
            LEFT JOIN s.user u
            WHERE u.id = :userId
            AND s.deleted = false
            """)
    Optional<Students> findByUserId(Long userId);

    @Override
    @Query("SELECT e FROM Students e WHERE e.deleted = false AND e.name IS NOT NULL")
    Page<Students> findAll(Pageable pageable);
}
