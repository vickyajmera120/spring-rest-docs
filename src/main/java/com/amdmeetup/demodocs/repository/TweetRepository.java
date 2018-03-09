package com.amdmeetup.demodocs.repository;

import com.amdmeetup.demodocs.entities.Tweet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends CrudRepository<Tweet, Long> {
}
