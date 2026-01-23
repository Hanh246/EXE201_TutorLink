package com.exe201.tutorlink.main.repository;

import com.exe201.tutorlink.common.repository.AbstractCrudRepository;
import com.exe201.tutorlink.main.entity.Tutors;
import com.exe201.tutorlink.main.entity.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends AbstractCrudRepository<Users, Long> {
}
