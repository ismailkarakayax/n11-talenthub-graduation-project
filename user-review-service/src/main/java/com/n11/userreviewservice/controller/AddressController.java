package com.n11.userreviewservice.controller;

import com.n11.userreviewservice.dto.request.create.CreateAddressRequest;

import com.n11.userreviewservice.dto.request.update.UpdateAddressRequest;

import com.n11.userreviewservice.dto.response.AddressResponse;

import com.n11.userreviewservice.general.RestResponse;
import com.n11.userreviewservice.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<RestResponse<AddressResponse>> save(@RequestBody @Valid CreateAddressRequest request) {
        AddressResponse response = addressService.save(request);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<AddressResponse>> findById(@PathVariable Long id) {
        AddressResponse response = addressService.findById(id);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RestResponse<AddressResponse>> updateById(@PathVariable Long id, @RequestBody @Valid UpdateAddressRequest request) {
        AddressResponse response = addressService.update(id, request);
        return new ResponseEntity<>(RestResponse.of(response), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        addressService.deleteById(id);
    }
}
