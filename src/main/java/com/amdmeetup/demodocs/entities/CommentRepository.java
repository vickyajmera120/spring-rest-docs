package com.amdmeetup.demodocs.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
  List<Comment> findByUserId(@Param("userId") Long userId);
}
