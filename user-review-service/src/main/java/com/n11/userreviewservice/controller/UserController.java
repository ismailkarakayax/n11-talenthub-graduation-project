package com.n11.userreviewservice.controller;

import com.n11.userreviewservice.dto.request.create.CreateUserRequest;
import com.n11.userreviewservice.dto.request.update.UpdateUserRequest;
import com.n11.userreviewservice.dto.response.UserResponse;
import com.n11.userreviewservice.general.RestResponse;
import com.n11.userreviewservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<RestResponse<UserResponse>> save(@RequestBody CreateUserRequest request) {
        UserResponse response = userService.save(request);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserResponse>> findById(@PathVariable Long id) {
        UserResponse response = userService.findById(id);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<UserResponse>> updateById(@PathVariable Long id, @RequestBody @Valid UpdateUserRequest request) {
        UserResponse response = userService.update(id, request);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }

    @PatchMapping("/{id}/deActivate")
    public ResponseEntity<RestResponse<UserResponse>> deActivateById(@PathVariable Long id) {
        UserResponse response = userService.deActivate(id);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
