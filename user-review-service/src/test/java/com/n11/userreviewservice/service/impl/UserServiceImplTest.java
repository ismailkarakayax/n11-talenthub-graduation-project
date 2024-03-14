package com.n11.userreviewservice.service.impl;

import com.n11.userreviewservice.dto.request.create.CreateUserRequest;
import com.n11.userreviewservice.dto.request.update.UpdateUserRequest;
import com.n11.userreviewservice.dto.response.UserResponse;
import com.n11.userreviewservice.mapper.UserMapper;
import com.n11.userreviewservice.model.User;
import com.n11.userreviewservice.model.enums.Gender;
import com.n11.userreviewservice.model.enums.Status;
import com.n11.userreviewservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getAll() {
        //given
        List<User> userList = Arrays.asList(
                new User(),
                new User()
        );
        List<UserResponse> responses= new ArrayList<>();

        UserResponse expectedResponse1 = new UserResponse(1L,"John", "Doe", LocalDate.of(2001, 3, 15), "john.doe@example.com", 40.0, -75.0, Gender.MALE, Status.ACTIVE);
        UserResponse expectedResponse2 = new UserResponse(2L,"Jane", "Doe", LocalDate.of(2002, 5, 20), "jane.doe@example.com", 41.0, -76.0, Gender.FEMALE, Status.ACTIVE);
        responses.add(expectedResponse1);
        responses.add(expectedResponse2);
        //when

        when(userRepository.findAll()).thenReturn(userList);
        when(userMapper.convertToUserResponseList(userList)).thenReturn(responses);

        List<UserResponse> result = userService.getAll();

        //then
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(userRepository, times(1)).findAll();
        verify(userMapper, times(1)).convertToUserResponseList(userList);
    }

    @Test
    void shouldSave() {
        //given
        CreateUserRequest createRequest = new CreateUserRequest("John", "Doe", LocalDate.of(2001, 3, 15), "john.doe@example.com", 40.0, -75.0, Gender.MALE);
        User user = new User(); // create a user instance as per your implementation
        UserResponse expectedResponse = new UserResponse(1L,"John", "Doe", LocalDate.of(2001, 3, 15), "john.doe@example.com", 40.0, -75.0, Gender.MALE, Status.ACTIVE);

        //when
        when(userMapper.convertCreateToUser(any(CreateUserRequest.class))).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(userMapper.convertToUserResponse(any(User.class))).thenReturn(expectedResponse);

        // Act
        UserResponse result = userService.save(createRequest);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponse, result);
        verify(userRepository, times(1)).save(any(User.class));
    }


    @Test
    void findById() {
        //given
        Long userId = 1L;
        User user = new User(); // create a user instance as per your implementation
        UserResponse expectedResponse = new UserResponse(1L,"John", "Doe", LocalDate.now(), "john.doe@example.com", 40.0, -75.0, Gender.MALE, Status.ACTIVE);

        //when
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.convertToUserResponse(any(User.class))).thenReturn(expectedResponse);

        // Act
        UserResponse result = userService.findById(userId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponse, result);
    }

    @Test
    void updateById() {
        // Arrange
        Long userId = 1L;
        UpdateUserRequest updateRequest = new UpdateUserRequest("UpdatedJohn", "UpdatedDoe", LocalDate.of(2001, 3, 15), "updated.john.doe@example.com", 42.0, -78.0, Gender.MALE);

        // Existing user with initial values
        User user = new User();
        user.setId(userId);
        user.setName("John");
        user.setSurname("Doe");
        user.setBirthDate(LocalDate.of(2001, 3, 15));
        user.setEmail("john.doe@example.com");
        user.setLatitude(40.0);
        user.setLongitude(-75.0);
        user.setGender(Gender.MALE);
        user.setStatus(Status.ACTIVE);

        // Updated user with new values
        User updatedUser = new User();
        updatedUser.setId(userId);
        updatedUser.setName("UpdatedJohn");
        updatedUser.setSurname("UpdatedDoe");
        updatedUser.setBirthDate(LocalDate.of(2001, 3, 15));
        updatedUser.setEmail("updated.john.doe@example.com");
        updatedUser.setLatitude(42.0);
        updatedUser.setLongitude(-78.0);
        updatedUser.setGender(Gender.MALE);
        updatedUser.setStatus(Status.ACTIVE);

        UserResponse expectedResponse = new UserResponse(1L,"UpdatedJohn", "UpdatedDoe", LocalDate.of(2001, 3, 15), "updated.john.doe@example.com", 42.0, -78.0, Gender.MALE, Status.ACTIVE);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.convertUptateToUser(user, updateRequest)).thenReturn(updatedUser);
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);
        when(userMapper.convertToUserResponse(updatedUser)).thenReturn(expectedResponse);

        // Act
        UserResponse result = userService.update(userId, updateRequest);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponse, result);
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(updatedUser);
    }

    @Test
    void deleteById() {
        // given
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setName("John");
        user.setSurname("Doe");
        user.setBirthDate(LocalDate.now());
        user.setEmail("john.doe@example.com");
        user.setLatitude(40.0);
        user.setLongitude(-75.0);
        user.setGender(Gender.MALE);
        user.setStatus(Status.ACTIVE);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        userService.deleteById(userId);

        // Assert
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void deActivate() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setName("John");
        user.setSurname("Doe");
        user.setBirthDate(LocalDate.now());
        user.setEmail("john.doe@example.com");
        user.setLatitude(40.0);
        user.setLongitude(-75.0);
        user.setGender(Gender.MALE);
        user.setStatus(Status.INACTIVE);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        // Act
        UserResponse result = userService.deActivate(userId);

        // Assert
        assertNotNull(user);
        assertEquals(Status.INACTIVE, user.getStatus());
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(user);
        verify(userMapper, times(1)).convertToUserResponse(user);
    }

    @Test
    void findEntityById() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setName("John");
        user.setSurname("Doe");
        user.setBirthDate(LocalDate.now());
        user.setEmail("john.doe@example.com");
        user.setLatitude(40.0);
        user.setLongitude(-75.0);
        user.setGender(Gender.MALE);
        user.setStatus(Status.ACTIVE);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        User result = userService.findEntityById(userId);

        // Assert
        assertNotNull(user);
        assertEquals(user, result);
        verify(userRepository, times(1)).findById(userId);
    }

}