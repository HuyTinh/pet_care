package com.pet_care.identity_service.controller;

import com.pet_care.identity_service.dto.request.AccountCreationRequest;
import com.pet_care.identity_service.dto.request.AccountUpdateRequest;
import com.pet_care.identity_service.dto.response.ApiResponse;
import com.pet_care.identity_service.dto.response.AccountResponse;
import com.pet_care.identity_service.service.AccountService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("account")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
    AccountService accountService;

    @GetMapping
    ApiResponse<List<AccountResponse>> getAllUser() {
        return ApiResponse.<List<AccountResponse>>builder().code(1000).result(accountService.getAllUser()).build();
    }

    @GetMapping("/{id}")
    ApiResponse<AccountResponse> getUserById(@PathVariable("id") Long id) {
        return ApiResponse.<AccountResponse>builder().code(1000).result(accountService.getUserById(id)).build();
    }

    @GetMapping("/email/{email}")
    ApiResponse<AccountResponse> getUserByEmail(@PathVariable("email") String email) {
        return ApiResponse.<AccountResponse>builder().code(1000).result(accountService.getUserByEmail(email)).build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ApiResponse<AccountResponse> createUser(@Valid @RequestBody AccountCreationRequest request) {
        return ApiResponse.<AccountResponse>builder().code(1000).result(accountService.createRequest(request)).build();
    }

    @PutMapping("/{id}")
    ApiResponse<AccountResponse> updateUser(@PathVariable("id") Long id, @RequestBody @Valid AccountUpdateRequest request) {
        return ApiResponse.<AccountResponse>builder().code(1000).result(accountService.updateRequest(id, request)).build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> deleteUser(@PathVariable("id") Long id) {
        accountService.deleteRequest(id);
        return ApiResponse.<String>builder().code(1000).message("Delete account successful").build();
    }

}
