package com.pet_care.identity_service.service;

import com.pet_care.identity_service.dto.request.AccountCreationRequest;
import com.pet_care.identity_service.dto.request.AccountUpdateRequest;
import com.pet_care.identity_service.dto.response.AccountResponse;
import com.pet_care.identity_service.exception.ErrorCode;
import com.pet_care.identity_service.exception.IdentityException;
import com.pet_care.identity_service.mapper.AccountMapper;
import com.pet_care.identity_service.model.Account;
import com.pet_care.identity_service.repository.AccountRepository;
import com.pet_care.identity_service.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {

    AccountRepository accountRepository;

    AccountMapper accountMapper;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    RoleRepository roleRepository;

    public List<AccountResponse> getAllUser() {
        return accountRepository.findAll().stream().map(accountMapper::toDto).collect(Collectors.toList());
    }

    public AccountResponse createRequest(AccountCreationRequest request) {
        if(accountRepository.existsByEmail(request.getEmail()))
            throw new IdentityException(ErrorCode.USER_EXISTED);

        Account account = accountMapper.toEntity(request);
        account.setPassword(passwordEncoder.encode(request.getPassword()));

        account.setRoles(new HashSet<>(roleRepository.findAllById(request.getRoles())));

        return accountMapper.toDto(accountRepository.save(account));
    }

    public AccountResponse updateRequest(Long id, AccountUpdateRequest request) {
        Account existAccount = accountRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
        return accountMapper.toDto(accountRepository.save(accountMapper.partialUpdate(request, existAccount)));
    }

    public void deleteRequest(Long id) {
        accountRepository.deleteById(id);
    }

    @PostAuthorize("returnObject.email == authentication.name || hasRole('HOSPITAL_ADMINISTRATOR')")
    public AccountResponse getUserById(Long id) {
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        authentication.getAuthorities().forEach(grantedAuthority -> System.out.println(grantedAuthority.getAuthority()));
        return accountMapper
                .toDto(accountRepository
                        .findById(id)
                        .orElseThrow(() -> new IdentityException(ErrorCode.USER_NOT_EXISTED)));
    }

    public AccountResponse getUserByEmail(String email) {
        return accountMapper
                .toDto(accountRepository
                        .findByEmail(email)
                        .orElseThrow(() -> new IdentityException(ErrorCode.USER_NOT_EXISTED)));
    }

}
