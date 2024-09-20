package com.pet_care.customer_service.mapper;

import com.pet_care.customer_service.dto.request.PetRequest;
import com.pet_care.customer_service.dto.response.PetResponse;
import com.pet_care.customer_service.model.Pet;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PetMapper {
    Pet toEntity(PetRequest petRequest);

    PetResponse toDto(Pet pet);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Pet partialUpdate(PetRequest petRequest, @MappingTarget Pet pet);
}