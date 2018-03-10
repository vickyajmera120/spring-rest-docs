package com.amdmeetup.demodocs.controller;

import com.amdmeetup.demodocs.entities.Tweet;
import com.amdmeetup.demodocs.repository.TweetRepository;

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
@RequestMapping(path = "/tweets", produces = MediaType.APPLICATION_JSON_VALUE)
public class TweetController {

  @Autowired
  private TweetRepository tweetRepository;

  @GetMapping(value = "/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<Tweet> getTweet(@PathVariable("id") final Long id) {

    return tweetRepository.findById(id)
        .map(tweet -> new ResponseEntity<>(tweet, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<Tweet> createTweet(@RequestBody final Tweet tweet) {

    return new ResponseEntity<>(tweetRepository.save(tweet), HttpStatus.CREATED);

  }

}
