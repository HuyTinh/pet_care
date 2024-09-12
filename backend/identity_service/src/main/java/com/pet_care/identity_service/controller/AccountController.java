package com.pet_care.identity_service.controller;

import com.pet_care.identity_service.dto.request.UserCreationRequest;
import com.pet_care.identity_service.dto.request.UserUpdateRequest;
import com.pet_care.identity_service.dto.response.ApiResponse;
import com.pet_care.identity_service.dto.response.UserResponse;
import com.pet_care.identity_service.entity.User;
import com.pet_care.identity_service.mapper.UserMapper;
import com.pet_care.identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
    UserService userService;

    @GetMapping
    ApiResponse<List<UserResponse>> getAllUser() {
        return ApiResponse.<List<UserResponse>>builder().code(1000).result(userService.getAllUser()).build();
    }

    @GetMapping("/{id}")
    UserResponse getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/email/{email}")
    ApiResponse<UserResponse> getUserByEmail(@PathVariable("email") String email) {
        return ApiResponse.<UserResponse>builder().code(1000).result(userService.getUserByEmail(email)).build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserResponse createUser(@Valid @RequestBody UserCreationRequest request) {
        return userService.createRequest(request);
    }

    @PutMapping("/{id}")
    UserResponse updateUser(@PathVariable("id") Long id,@RequestBody @Valid UserUpdateRequest request) {
        return userService.updateRequest(id, request);
    }

    @DeleteMapping("/{id}")
    Map<String, String> deleteUser(@PathVariable("id") Long id) {
        userService.deleteRequest(id);
        return Map.of("message", "User deleted successfully");
    }

}
