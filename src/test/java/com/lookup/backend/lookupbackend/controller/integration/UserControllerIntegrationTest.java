package com.lookup.backend.lookupbackend.controller.integration;

import com.lookup.backend.lookupbackend.controller.UserController;
import com.lookup.backend.lookupbackend.model.usermodel.User;
import com.lookup.backend.lookupbackend.repository.FileRepository;
import com.lookup.backend.lookupbackend.repository.UserRepository;
import com.lookup.backend.lookupbackend.service.fileservice.FileService;
import com.lookup.backend.lookupbackend.service.observationservice.ObservationService;
import com.lookup.backend.lookupbackend.service.userservice.UserDetailsServiceImpl;
import com.lookup.backend.lookupbackend.service.userservice.UserServiceImpl;
import com.lookup.backend.lookupbackend.utilities.JWTUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class UserControllerIntegrationTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private UserServiceImpl userServiceImpl;

    @MockBean
    private JWTUtility jwtUtility;

    @MockBean
    private FileRepository fileRepository;

    @MockBean
    private FileService fileService;

    @MockBean
    private ObservationService observationService;

    @Autowired
    UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(userController);
    }

    @Test
    void requestToGetUserByNameWithoutTokenShouldReturnUnauthorized() throws IOException {
       TestRestTemplate restTemplate = new TestRestTemplate();
       ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/users/user/name/admin", String.class);
       assertThat(response.getStatusCode(), equalTo(HttpStatus.UNAUTHORIZED));
    }

    @Test
    void createUserRequestShouldReturnBadRequestErrorIfNameIsNull() throws Exception {
        User user = new User();

        user.setName("");
        user.setPassWord("password");
        user.setEmailAdress("emailadress");

        mockMvc.perform(
                post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(user)))
                .andExpect(status().isBadRequest());
    }

}
