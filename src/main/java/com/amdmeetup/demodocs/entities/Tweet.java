package com.amdmeetup.demodocs.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
public class Tweet {
    @Id
    @GeneratedValue
    Long id;
    @OneToOne
    User user;
    String content;
}
