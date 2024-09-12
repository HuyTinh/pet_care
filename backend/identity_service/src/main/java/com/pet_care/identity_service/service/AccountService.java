package com.pet_care.identity_service.service;

import com.pet_care.identity_service.dto.request.AccountCreationRequest;
import com.pet_care.identity_service.dto.request.AccountUpdateRequest;
import com.pet_care.identity_service.dto.response.AccountResponse;
import com.pet_care.identity_service.entity.Account;
import com.pet_care.identity_service.exception.ErrorCode;
import com.pet_care.identity_service.exception.IdentityException;
import com.pet_care.identity_service.mapper.AccountMapper;
import com.pet_care.identity_service.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {

    AccountRepository accountRepository;

    AccountMapper accountMapper;

    PasswordEncoder passwordEncoder;

    public List<AccountResponse> getAllUser() {
        return accountRepository.findAll().stream().map(accountMapper::toDto).collect(Collectors.toList());
    }

    public AccountResponse createRequest(AccountCreationRequest request) {
        if(accountRepository.existsByEmail(request.getEmail()))
            throw new IdentityException(ErrorCode.USER_EXISTED);

        Account account = accountMapper.toEntity(request);
        account.setPassword(passwordEncoder.encode(request.getPassword()));
        return accountMapper.toDto(accountRepository.save(account));
    }

    public AccountResponse updateRequest(Long id, AccountUpdateRequest request) {
        Account existAccount = accountRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
        return accountMapper.toDto(accountRepository.save(accountMapper.partialUpdate(request, existAccount)));
    }

    public void deleteRequest(Long id) {
        accountRepository.deleteById(id);
    }

    public AccountResponse getUserById(Long id) {
        return accountMapper
                .toDto(accountRepository
                        .findById(id)
                        .orElseThrow(() -> new RuntimeException("Account not found")));
    }

    public AccountResponse getUserByEmail(String email) {
        return accountMapper
                .toDto(accountRepository
                        .findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("Account not found")));
    }

}
