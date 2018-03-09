package com.amdmeetup.demodocs.repository;

import com.amdmeetup.demodocs.entities.Comment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

  List<Comment> findByUserId(@Param("userId") Long userId);

}
