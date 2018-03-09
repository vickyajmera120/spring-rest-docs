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

import com.amdmeetup.demodocs.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    User user1 = User.builder().id(1L).name("John").email("john@example.com").build();

    api.perform(post("/users").contentType(MediaType.APPLICATION_JSON_VALUE)
        .accept(MediaType.APPLICATION_JSON_VALUE)
        .content(objectMapper.writeValueAsString(user1)))
        .andExpect(status().isCreated())
        .andDo(document("create-user",
            requestFields(
                fieldWithPath("id").description("id of the newly created user"),
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

}
