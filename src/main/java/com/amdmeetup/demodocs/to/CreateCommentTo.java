package com.amdmeetup.demodocs.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentTo {

  Long tweetId;
  Long userId;
  String content;

}
