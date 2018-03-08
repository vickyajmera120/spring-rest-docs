package com.amdmeetup.demodocs;


import com.amdmeetup.demodocs.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {DemodocsApplication.class})
@AutoConfigureRestDocs("target/snippets")
@AutoConfigureMockMvc
public class DemoDocsIT {

    @Autowired
    private MockMvc api;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createUserWithSuccess() throws Exception {
        {
            User user1 = User.builder().id(1l).name("John").email("john@example.com").build();

            MvcResult user1Result = api.perform(post("/users").contentType(MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                    .content(objectMapper.writeValueAsString(user1)))
                    .andExpect(status().isCreated())
                    .andDo(MockMvcRestDocumentation.document("{method-name}-{step}")).andReturn();

        }
    }

}
