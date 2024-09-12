package com.pet_care.identity_service.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.pet_care.identity_service.dto.request.AuthenticationRequest;
import com.pet_care.identity_service.dto.request.IntrospectRequest;
import com.pet_care.identity_service.dto.response.AuthenticationResponse;
import com.pet_care.identity_service.dto.response.IntrospectResponse;
import com.pet_care.identity_service.exception.ErrorCode;
import com.pet_care.identity_service.exception.IdentityException;
import com.pet_care.identity_service.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    AccountRepository accountRepository;

    PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY);

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        return IntrospectResponse
                .builder()
                .valid(verified && expiryTime.after(new Date()))
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var account = accountRepository.findByEmail(request.getEmail()).orElseThrow(() -> new IdentityException(ErrorCode.USER_NOT_EXISTED));

        boolean authenticated = passwordEncoder.matches(request.getPassword(), account.getPassword());

        if(!authenticated) {
            throw new IdentityException(ErrorCode.UNAUTHENTICATED);
        }

        var token =generateToken(request.getEmail());

        return AuthenticationResponse.builder()
                .token(token)
                .isAuthenticated(true)
                .build();
    }

    private String generateToken(String email) {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(email)
                .issuer("pet_care")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(30, ChronoUnit.MINUTES).toEpochMilli()
                ))
                .claim("customClaim", "custom")
                .build();


        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

       try {
           jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
        return jwsObject.serialize();
       } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new IdentityException(ErrorCode.UNAUTHENTICATED);
       }
    }
}
