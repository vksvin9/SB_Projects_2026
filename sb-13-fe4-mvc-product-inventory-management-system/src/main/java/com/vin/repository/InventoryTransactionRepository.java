package com.vin.repository;

import com.vin.entity.InventoryTransaction;
import com.vin.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryTransactionRepository
        extends JpaRepository<InventoryTransaction, Long> {

    List<InventoryTransaction> findByProduct(Product product);
}