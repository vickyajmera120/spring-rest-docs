package com.amdmeetup.demodocs;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.amdmeetup.demodocs.entities.Comment;
import com.amdmeetup.demodocs.entities.Tweet;
import com.amdmeetup.demodocs.entities.User;
import com.amdmeetup.demodocs.to.CreateCommentTo;
import com.amdmeetup.demodocs.to.CreateTweetTo;
import com.amdmeetup.demodocs.to.CreateUserTo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureRestDocs("target/snippets")
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DemoDocsIT {

  @Autowired
  private MockMvc api;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void test01CreateUser() throws Exception {
    CreateUserTo userTo = CreateUserTo.builder().name("John").email("john@example.com").build();

    api.perform(post("/users").contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsString(userTo)))
        .andExpect(status().isCreated())
        .andDo(document("create-user",
            requestFields(
                fieldWithPath("name").description("name of the user"),
                fieldWithPath("email").description("email of the user")
            ),
            responseFields(
                fieldWithPath("id").description("id of the newly created user"),
                fieldWithPath("name").description("name of the user"),
                fieldWithPath("email").description("email of the user")
            )));

  }

  @Test
  public void test02GetUser() throws Exception {

    api.perform(get("/users/{id}", 1L))
        .andExpect(status().isOk())
        .andDo(document("get-user",
            pathParameters(
                parameterWithName("id").description("id of the user")
            ),
            responseFields(
                fieldWithPath("id").description("id of the newly created user"),
                fieldWithPath("name").description("name of the user"),
                fieldWithPath("email").description("email of the user")
            )));
  }

  @Test
  public void test03CreateTweet() throws Exception {

    ObjectReader toUser = objectMapper.readerFor(User.class);
    ObjectReader toTweet = objectMapper.readerFor(Tweet.class);


    CreateUserTo userTo = CreateUserTo.builder().name("John").email("john@example.com").build();

    MvcResult createUserResponse = api.perform(post("/users").contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsString(userTo)))
        .andExpect(status().isCreated())
        .andDo(document("create-user-for-tweet",
            requestFields(
                fieldWithPath("name").description("name of the user"),
                fieldWithPath("email").description("email of the user")
            ),
            responseFields(
                fieldWithPath("id").description("id of the newly created user"),
                fieldWithPath("name").description("name of the user"),
                fieldWithPath("email").description("email of the user")
            ))).andReturn();


    User existingUser = toUser.readValue(createUserResponse.getResponse().getContentAsString());
    CreateTweetTo createTweet = CreateTweetTo.builder().userId(existingUser.getId())
        .content("Test Tweet").build();

    MvcResult createTweetResponse = api.perform(post("/tweets")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsString(createTweet)))
        .andExpect(status().isCreated())
        .andDo(document("create-tweet",
            requestFields(
                fieldWithPath("userId").description("id of the user who is tweeting"),
                fieldWithPath("content").description("content of tweet")
            ),
            responseFields(
                fieldWithPath("id").description("id of the newly created tweet"),
                fieldWithPath("userId").description("name of the user"),
                fieldWithPath("content").description("email of the user")
            ))).andReturn();
  }

  @Test
  public void test04CreateComment() throws Exception {

    ObjectReader toUser = objectMapper.readerFor(User.class);
    ObjectReader toTweet = objectMapper.readerFor(Tweet.class);
    ObjectReader toComment = objectMapper.readerFor(Comment.class);

    CreateUserTo userTo = CreateUserTo.builder().name("John").email("john@example.com").build();

    MvcResult createUserResponse = api.perform(post("/users").contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsString(userTo)))
        .andExpect(status().isCreated())
        .andDo(document("create-user-for-comment",
            requestFields(
                fieldWithPath("name").description("name of the user"),
                fieldWithPath("email").description("email of the user")
            ),
            responseFields(
                fieldWithPath("id").description("id of the newly created user"),
                fieldWithPath("name").description("name of the user"),
                fieldWithPath("email").description("email of the user")
            ))).andReturn();


    User createduser = toUser.readValue(createUserResponse.getResponse().getContentAsString());
    CreateTweetTo createTweet = CreateTweetTo.builder().userId(createduser.getId())
        .content("Test Tweet").build();

    MvcResult createTweetResponse = api.perform(post("/tweets")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsString(createTweet)))
        .andExpect(status().isCreated())
        .andDo(document("create-tweet-for-comment",
            requestFields(
                fieldWithPath("userId").description("id of the user who is tweeting"),
                fieldWithPath("content").description("content of tweet")
            ),
            responseFields(
                fieldWithPath("id").description("id of the newly created tweet"),
                fieldWithPath("userId").description("name of the user"),
                fieldWithPath("content").description("email of the user")
            ))).andReturn();


    Tweet createdTweet = toTweet.readValue(createTweetResponse.getResponse().getContentAsString());
    CreateCommentTo createComment = CreateCommentTo.builder().tweetId(createdTweet.getId())
        .userId(createduser.getId()).content("Test Comment").build();

    MvcResult createCommentResponse = api.perform(post("/comments")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsString(createComment)))
        .andExpect(status().isCreated())
        .andDo(document("create-comment",
            requestFields(
                fieldWithPath("userId").description("id of the user who is commenting"),
                fieldWithPath("tweetId").description("id of the tweet on which user is commenting"),
                fieldWithPath("content").description("content of comment")
            ),
            responseFields(
                fieldWithPath("id").description("id of the newly created comment"),
                fieldWithPath("userId").description("id of the user who commented"),
                fieldWithPath("content").description("content of the comment"),
                fieldWithPath("tweetId").description("id of the tweet on which user commented")
            ))).andReturn();

  }

}