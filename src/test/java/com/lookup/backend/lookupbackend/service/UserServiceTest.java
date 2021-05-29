package com.lookup.backend.lookupbackend.service;

import com.lookup.backend.lookupbackend.model.usermodel.User;
import com.lookup.backend.lookupbackend.repository.UserRepository;
import com.lookup.backend.lookupbackend.service.userservice.UserService;
import com.lookup.backend.lookupbackend.service.userservice.UserServiceImpl;
import org.checkerframework.common.value.qual.ArrayLenRange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private final UserService userService = new UserServiceImpl();

    @Mock
    private UserRepository userRepository;

    @Test
    void userRepositoryNotNull(){
        Assertions.assertNotNull(userRepository);
    }

    @Test
    void userServiceNotNull(){
        Assertions.assertNotNull(userService);
    }

    @Test
    public void getAllUsersRequestShouldReturnListOfUsers(){
        //Arrange
        Mockito.when(userService.getAllUsers()).thenReturn(new ArrayList<User>());

        //Act
        List<User> userList = userRepository.findAll();

        //Assert
        Assertions.assertFalse(userList.isEmpty());
    }

    @Test
    void correctUserSaveRequestShouldReturnStatusOK() throws Exception {
        //Arrange
        User user = new User();
        user.setPassWord("passWord");
        user.setName("name");
        user.setEmailAdress("name@name.name");

        //Act
//        ResponseEntity responseEntity = Mockito.when(userService.save(user).);

        //Assert
//        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

   @Test
    void noUserNameInRequestShouldReturnException() throws Exception {
       //Arrange
       User user = new User();
       user.setName("");
       user.setEmailAdress("mail@mail.mail");
       user.setPassWord("password");

       //Act
       ResponseEntity response = userService.save(user);

       //Assert
       Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
       Assertions.assertEquals("Username cannot be null", response.getBody());
   }

   @Test
    void noPassWordInRequestShouldReturnException() throws Exception{
       //Arrange
       User user = new User();
       user.setName("name");
       user.setEmailAdress("mail@mail.mail");
       user.setPassWord("");

       //Act
       ResponseEntity response = userService.save(user);


       //Assert
       Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
   }



}
