package com.vin.service.impl;

import com.vin.entity.Expense;
import com.vin.repository.ExpenseRepository;
import com.vin.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Override
    public Expense saveExpense(Expense expense) {

        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getAllExpenses() {

        return expenseRepository.findAll();
    }

    @Override
    public Double getTotalExpenses() {

        return expenseRepository.findAll()
                .stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    @Override
    public List<Expense> getExpensesByCategory(String category) {

        return expenseRepository.findByCategory(category);
    }

    @Override
    public void deleteExpense(Long id) {

        expenseRepository.deleteById(id);
    }
}