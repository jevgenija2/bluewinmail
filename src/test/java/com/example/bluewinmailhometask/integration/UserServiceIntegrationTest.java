package com.example.bluewinmailhometask.integration;

import com.example.bluewinmailhometask.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.io.FileNotFoundException;

import static com.example.bluewinmailhometask.commonMethods.CommonMethods.getJsonMockData;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles({"test"})
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceIntegrationTest {

    private static final String USERS_URL = "/users";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;

    @BeforeEach
    public void setupMockBehaviour() throws FileNotFoundException {
        when(userService.getUserList()).thenReturn(getJsonMockData());
    }

    @Test
    void testGetAllUsers() throws Exception {
        mockMvc.perform(get(
                    USERS_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.users[0].id", is(1)))
                .andExpect(jsonPath("$.users[0].name", is("Sam")))
                .andExpect(jsonPath("$.users[0].emailAddress", is("samuel.wright@testMail.com")));
    }

}
