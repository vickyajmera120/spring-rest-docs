package com.amdmeetup.demodocs.controller;

import com.amdmeetup.demodocs.entities.Comment;
import com.amdmeetup.demodocs.repository.CommentRepository;
import com.amdmeetup.demodocs.to.CreateCommentTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/comments", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {

  @Autowired
  private CommentRepository commentRepository;

  @GetMapping(value = "/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<Comment> getComment(@PathVariable("id") final Long id) {

    return commentRepository.findById(id)
        .map(comment -> new ResponseEntity<>(comment, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<Comment> createComment(@RequestBody final CreateCommentTo commentTo) {

    final Comment comment = Comment.builder()
        .tweetId(commentTo.getTweetId())
        .userId(commentTo.getUserId())
        .content(commentTo.getContent())
        .build();
    return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.CREATED);
  }

}
