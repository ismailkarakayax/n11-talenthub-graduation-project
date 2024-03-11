package com.n11.userreviewservice.service;

import com.n11.userreviewservice.dto.request.create.CreateUserRequest;
import com.n11.userreviewservice.dto.request.update.UpdateUserRequest;
import com.n11.userreviewservice.dto.response.UserResponse;
import com.n11.userreviewservice.model.User;

public interface UserService {

    UserResponse save(CreateUserRequest request);

    UserResponse findById(Long id);

    UserResponse update(Long id,UpdateUserRequest request);

    void deleteById(Long id);

    UserResponse deActivate(Long id);
    User findEntityById(Long id);

}
