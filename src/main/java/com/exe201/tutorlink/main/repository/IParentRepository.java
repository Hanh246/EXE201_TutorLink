package com.exe201.tutorlink.main.repository;

import com.exe201.tutorlink.common.repository.AbstractCrudRepository;
import com.exe201.tutorlink.main.entity.Parents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IParentRepository extends AbstractCrudRepository<Parents, Long> {
    @Override
    @Query("SELECT e FROM Parents e WHERE e.deleted = false AND e.name IS NOT NULL")
    Page<Parents> findAll(Pageable pageable);
}
