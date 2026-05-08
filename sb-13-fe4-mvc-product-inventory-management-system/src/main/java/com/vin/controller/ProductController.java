package com.vin.controller;

import com.vin.entity.Product;

import com.vin.service.ProductService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    // Dashboard
    @GetMapping("/")
    public String dashboard(
            Model model,
            @RequestParam(required = false)
            String keyword) {

        if (keyword != null &&
                !keyword.isEmpty()) {

            model.addAttribute(
                    "products",
                    service.searchProducts(keyword)
            );

        } else {

            model.addAttribute(
                    "products",
                    service.getAllProducts()
            );
        }

        return "index";
    }

    // Add Product Form
    @GetMapping("/add")
    public String addForm(Model model) {

        model.addAttribute(
                "product",
                new Product()
        );

        return "add-product";
    }

    // Save Product
    @PostMapping("/save")
    public String saveProduct(
            @Valid @ModelAttribute Product product,
            BindingResult result) {

        if (result.hasErrors()) {
            return "add-product";
        }

        service.addProduct(product);

        return "redirect:/";
    }

    // Edit Product Form
    @GetMapping("/edit/{id}")
    public String editForm(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "product",
                service.getProductById(id)
        );

        return "edit-product";
    }

    // Update Product
    @PostMapping("/update")
    public String updateProduct(
            @Valid @ModelAttribute Product product,
            BindingResult result) {

        if (result.hasErrors()) {
            return "edit-product";
        }

        service.updateProduct(product);

        return "redirect:/";
    }

    // Delete Product
    @GetMapping("/delete/{id}")
    public String deleteProduct(
            @PathVariable Long id) {

        service.deleteProduct(id);

        return "redirect:/";
    }

    // Stock Form
    @GetMapping("/stock/{id}")
    public String stockForm(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "product",
                service.getProductById(id)
        );

        return "stock-form";
    }

    // Update Stock
    @PostMapping("/stock/update")
    public String updateStock(
            @RequestParam Long productId,
            @RequestParam String action,
            @RequestParam int quantity,
            RedirectAttributes redirectAttributes) {

        try {

            if ("ADD".equals(action)) {

                service.addStock(productId, quantity);

                redirectAttributes.addFlashAttribute(
                        "successMessage",
                        "Stock added successfully"
                );

            } else if ("REMOVE".equals(action)) {

                service.removeStock(productId, quantity);

                redirectAttributes.addFlashAttribute(
                        "successMessage",
                        "Stock removed successfully"
                );
            }

        } catch (RuntimeException exception) {

            redirectAttributes.addFlashAttribute(
                    "errorMessage",
                    exception.getMessage()
            );

            return "redirect:/stock/" + productId;
        }

        return "redirect:/";
    }

    // Transaction History
    @GetMapping("/history/{id}")
    public String transactionHistory(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "transactions",
                service.getTransactionHistory(id)
        );

        return "history";
    }
}