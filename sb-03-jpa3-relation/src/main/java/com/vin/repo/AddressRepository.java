package com.vin.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vin.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {}
