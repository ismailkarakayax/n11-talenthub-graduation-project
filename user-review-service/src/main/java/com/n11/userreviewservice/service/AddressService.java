package com.n11.userreviewservice.service;

import com.n11.userreviewservice.dto.request.create.CreateAddressRequest;
import com.n11.userreviewservice.dto.request.update.UpdateAddressRequest;
import com.n11.userreviewservice.dto.response.AddressResponse;

public interface AddressService {
    AddressResponse save(CreateAddressRequest request);

    AddressResponse findById(Long id);

    AddressResponse update(Long id, UpdateAddressRequest request);

    void deleteById(Long id);
}
