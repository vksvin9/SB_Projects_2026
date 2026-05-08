package com.vin.controller;

import com.vin.entity.Expense;
import com.vin.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping("/")
    public String dashboard(
            @RequestParam(required = false) String category,
            Model model
    ) {

        if (category != null && !category.isEmpty()) {

            model.addAttribute(
                    "expenses",
                    expenseService.getExpensesByCategory(category)
            );

        } else {

            model.addAttribute(
                    "expenses",
                    expenseService.getAllExpenses()
            );
        }

        model.addAttribute(
                "total",
                expenseService.getTotalExpenses()
        );

        return "index";
    }

    @GetMapping("/add-expense")
    public String addExpensePage(Model model) {

        model.addAttribute("expense", new Expense());

        return "add-expense";
    }

    @PostMapping("/expenses")
    public String saveExpense(
            @Valid @ModelAttribute Expense expense,
            BindingResult result
    ) {

        if (result.hasErrors()) {

            return "add-expense";
        }

        expenseService.saveExpense(expense);

        return "redirect:/";
    }

    @PostMapping("/expenses/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {

        expenseService.deleteExpense(id);

        return "redirect:/";
    }
}