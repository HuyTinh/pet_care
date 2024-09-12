package com.pet_care.identity_service.mapper;

import com.pet_care.identity_service.dto.request.AccountCreationRequest;
import com.pet_care.identity_service.dto.request.AccountUpdateRequest;
import com.pet_care.identity_service.dto.response.AccountResponse;
import com.pet_care.identity_service.entity.Account;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {
    Account toEntity(AccountCreationRequest accountCreationRequest);

    AccountResponse toDto(Account account);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Account partialUpdate(AccountUpdateRequest userCreationRequest, @MappingTarget Account account);
}