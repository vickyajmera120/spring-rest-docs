package com.amdmeetup.demodocs.controller;

import com.amdmeetup.demodocs.entities.User;
import com.amdmeetup.demodocs.repository.UserRepository;
import com.amdmeetup.demodocs.to.CreateUserTo;

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
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping(value = "/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<User> getUser(@PathVariable("id") final Long id) {

    return userRepository.findById(id)
        .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<User> createUser(@RequestBody final CreateUserTo createUserTo) {

    final User user = User.builder()
        .name(createUserTo.getName())
        .email(createUserTo.getEmail())
        .build();
    return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);

  }

}
