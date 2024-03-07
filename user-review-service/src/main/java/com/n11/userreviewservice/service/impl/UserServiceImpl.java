package com.n11.userreviewservice.service.impl;

import com.n11.userreviewservice.dto.request.create.CreateUserRequest;
import com.n11.userreviewservice.dto.request.update.UpdateUserRequest;
import com.n11.userreviewservice.dto.response.UserResponse;
import com.n11.userreviewservice.exception.UserNotFoundException;
import com.n11.userreviewservice.general.GeneralErrorMessage;
import com.n11.userreviewservice.mapper.UserMapper;
import com.n11.userreviewservice.model.User;
import com.n11.userreviewservice.model.enums.Status;
import com.n11.userreviewservice.repository.UserRepository;
import com.n11.userreviewservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.n11.userreviewservice.general.GeneralErrorMessage.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse save(CreateUserRequest request) {
        User user = userMapper.convertCreateToUser(request);
        userRepository.save(user);
        return userMapper.convertToUserResponse(user);
    }

    @Override
    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        return userMapper.convertToUserResponse(user);
    }

    @Override
    public UserResponse update(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        User updatedUser = userMapper.convertUptateToUser(user, request);

        return userMapper.convertToUserResponse(updatedUser);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse deActivate(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        user.setStatus(Status.INACTIVE);
        userRepository.save(user);
        return userMapper.convertToUserResponse(user);
    }

    @Override
    public User findEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));


    }
}
