package com.n11.userreviewservice.repository;

import com.n11.userreviewservice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Long> {
}
