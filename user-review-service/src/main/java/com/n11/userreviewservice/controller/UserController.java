package com.n11.userreviewservice.controller;

import com.n11.userreviewservice.dto.request.create.CreateUserRequest;
import com.n11.userreviewservice.dto.request.update.UpdateUserRequest;
import com.n11.userreviewservice.dto.response.UserResponse;
import com.n11.userreviewservice.general.RestResponse;
import com.n11.userreviewservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get all users by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found")
    })
    @GetMapping
    public ResponseEntity<RestResponse<List<UserResponse>>> getAll() {
        List<UserResponse> response = userService.getAll();
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Request body to create a new user",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CreateUserRequest.class),
                    examples = {
                            @ExampleObject(
                                    name = "new user",
                                    summary = "CreateUserRequest",
                                    description = "Complete request with all available " +
                                            "fields for User",
                                    value = """
                                            {
                                            "name": "John",
                                            "surname": "Doe",
                                            "birthDate": "1990-01-01",
                                            "email": "john.doe@example.com",
                                            "latitude": 41.06756165359459,
                                            "longitude": 28.899420853794673,
                                            "gender": "MALE"
                                            }"""
                            )
                    }

            )
    )
    @PostMapping
    public ResponseEntity<RestResponse<UserResponse>> save(@RequestBody CreateUserRequest request) {
        UserResponse response = userService.save(request);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.CREATED);
    }

    @Operation(summary = "Get user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserResponse>> findById(@PathVariable Long id) {
        UserResponse response = userService.findById(id);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }
    @Operation(summary = "Update user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Request body to create a new user",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CreateUserRequest.class),
                    examples = {
                            @ExampleObject(
                                    name = "update user",
                                    summary = "UpdateUserRequest",
                                    description = "Complete request with all available " +
                                            "fields for User",
                                    value = """
                                            {
                                            "name": "John",
                                            "surname": "Doe",
                                            "birthDate": "1990-01-01",
                                            "email": "john.doe@example.com",
                                            "latitude": 41.06756165359459,
                                            "longitude": 28.899420853794673,
                                            "gender": "MALE"
                                            }"""
                            )
                    }

            )
    )
    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<UserResponse>> updateById(@PathVariable Long id, @RequestBody @Valid UpdateUserRequest request) {
        UserResponse response = userService.update(id, request);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }

    @Operation(summary = "Deactivate user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deactivated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PatchMapping("/{id}/deActivate")
    public ResponseEntity<RestResponse<UserResponse>> deActivateById(@PathVariable Long id) {
        UserResponse response = userService.deActivate(id);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }

    @Operation(summary = "Delete user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
