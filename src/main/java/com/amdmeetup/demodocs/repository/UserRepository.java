package com.amdmeetup.demodocs.repository;

import com.amdmeetup.demodocs.entities.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
