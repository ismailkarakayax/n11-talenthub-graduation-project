package com.n11.userreviewservice.service.impl;

import com.n11.userreviewservice.dto.request.create.CreateAddressRequest;
import com.n11.userreviewservice.dto.request.update.UpdateAddressRequest;
import com.n11.userreviewservice.dto.response.AddressResponse;
import com.n11.userreviewservice.exception.AddressNotFoundException;
import com.n11.userreviewservice.exception.ReviewNotFoundException;
import com.n11.userreviewservice.mapper.AddressMapper;
import com.n11.userreviewservice.model.Address;

import com.n11.userreviewservice.model.User;
import com.n11.userreviewservice.repository.AddressRepository;
import com.n11.userreviewservice.service.AddressService;
import com.n11.userreviewservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.n11.userreviewservice.general.GeneralErrorMessage.ADDRESS_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final UserService userService;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public AddressResponse save(CreateAddressRequest request) {
        User entity = userService.findEntityById(request.userId());
        Address address = addressMapper.convertCreateToAddress(request, entity);
        addressRepository.save(address);
        return addressMapper.convertToResponse(address);
    }

    @Override
    public AddressResponse findById(Long id) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(ADDRESS_NOT_FOUND));
        return addressMapper.convertToResponse(address);
    }

    @Override
    public AddressResponse update(Long id, UpdateAddressRequest request) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(ADDRESS_NOT_FOUND));
        address = addressMapper.convertUpdateToReview(address, request);
        return addressMapper.convertToResponse(address);
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}
