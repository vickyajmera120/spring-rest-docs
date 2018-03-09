package com.amdmeetup.demodocs.repository;

import com.amdmeetup.demodocs.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
