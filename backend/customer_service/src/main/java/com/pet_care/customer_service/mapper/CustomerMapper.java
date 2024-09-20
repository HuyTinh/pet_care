package com.pet_care.customer_service.mapper;

import com.pet_care.customer_service.dto.request.CustomerRequest;
import com.pet_care.customer_service.dto.response.CustomerResponse;
import com.pet_care.customer_service.model.Customer;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
//    @Mapping(source = "appointment", ignore = true)
    Customer toEntity(CustomerRequest customerRequest);

    @AfterMapping
    default void linkPets(@MappingTarget Customer customer) {
        customer.getPets().forEach(pet -> pet.setCustomer(customer));
    }

    CustomerResponse toDto(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer partialUpdate(CustomerRequest customerRequest, @MappingTarget Customer customer);
}