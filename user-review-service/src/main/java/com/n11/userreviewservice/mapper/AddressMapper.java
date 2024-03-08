package com.n11.userreviewservice.mapper;

import com.n11.userreviewservice.dto.request.create.CreateAddressRequest;
import com.n11.userreviewservice.dto.request.update.UpdateAddressRequest;
import com.n11.userreviewservice.dto.response.AddressResponse;
import com.n11.userreviewservice.model.Address;
import com.n11.userreviewservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AddressMapper {
    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    Address convertCreateToAddress(CreateAddressRequest request, User user);

    Address convertUpdateToReview(@MappingTarget Address oldAddress, UpdateAddressRequest request);

    @Mapping(target = "userId",source = "address.user.id")
    AddressResponse convertToResponse(Address address);
}
