package com.vin.service;

import com.vin.entity.InventoryTransaction;
import com.vin.entity.Product;

import com.vin.repository.InventoryTransactionRepository;
import com.vin.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final InventoryTransactionRepository transactionRepository;

    // Get All Products
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    // Add Product
    public Product addProduct(Product product) {

        Product savedProduct =
                productRepository.save(product);

        InventoryTransaction transaction =
                InventoryTransaction.builder()
                        .transactionType("STOCK_IN")
                        .quantity(product.getQuantity())
                        .transactionDate(LocalDateTime.now())
                        .product(savedProduct)
                        .build();

        transactionRepository.save(transaction);

        return savedProduct;
    }

    // Update Product
    public Product updateProduct(Product product) {

        return productRepository.save(product);
    }

    // Get Product By ID
    public Product getProductById(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));
    }

    // Delete Product
    @Transactional
    public void deleteProduct(Long id) {

        Product product = getProductById(id);

        // Delete Transaction History First
        List<InventoryTransaction> transactions =
                transactionRepository.findByProduct(product);

        transactionRepository.deleteAll(transactions);

        // Delete Product
        productRepository.delete(product);
    }

    // Search Product
    public List<Product> searchProducts(String keyword) {

        return productRepository
                .findByNameContainingIgnoreCase(keyword);
    }

    // Add Stock
    public void addStock(Long productId,
                         int quantity) {

        Product product =
                getProductById(productId);

        product.setQuantity(
                product.getQuantity() + quantity
        );

        productRepository.save(product);

        InventoryTransaction transaction =
                InventoryTransaction.builder()
                        .transactionType("STOCK_IN")
                        .quantity(quantity)
                        .transactionDate(LocalDateTime.now())
                        .product(product)
                        .build();

        transactionRepository.save(transaction);
    }

    // Remove Stock
    public void removeStock(Long productId,
                            int quantity) {

        Product product =
                getProductById(productId);

        if (product.getQuantity() < quantity) {

            throw new RuntimeException(
                    "Insufficient stock"
            );
        }

        product.setQuantity(
                product.getQuantity() - quantity
        );

        productRepository.save(product);

        InventoryTransaction transaction =
                InventoryTransaction.builder()
                        .transactionType("STOCK_OUT")
                        .quantity(quantity)
                        .transactionDate(LocalDateTime.now())
                        .product(product)
                        .build();

        transactionRepository.save(transaction);
    }

    // Transaction History
    public List<InventoryTransaction>
    getTransactionHistory(Long productId) {

        Product product =
                getProductById(productId);

        return transactionRepository
                .findByProduct(product);
    }
}