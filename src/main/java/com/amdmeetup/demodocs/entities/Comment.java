package com.amdmeetup.demodocs.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue
    Long id;
    @OneToOne
    Tweet tweet;
    @OneToOne
    User user;
    String content;
}
