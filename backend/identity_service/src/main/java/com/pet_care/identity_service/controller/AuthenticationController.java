package com.pet_care.identity_service.controller;

import com.nimbusds.jose.JOSEException;
import com.pet_care.identity_service.dto.request.AuthenticationRequest;
import com.pet_care.identity_service.dto.request.IntrospectRequest;
import com.pet_care.identity_service.dto.response.ApiResponse;
import com.pet_care.identity_service.dto.response.AuthenticationResponse;
import com.pet_care.identity_service.dto.response.IntrospectResponse;
import com.pet_care.identity_service.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.authenticate(request))
                .build();
    }

    @PostMapping("introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        return ApiResponse.<IntrospectResponse>builder()
                .result(authenticationService.introspect(request))
                .build();
    }
}
