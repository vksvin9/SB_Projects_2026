package com.vin.service;

import com.vin.entity.Expense;

import java.util.List;

public interface ExpenseService {

    Expense saveExpense(Expense expense);

    List<Expense> getAllExpenses();

    Double getTotalExpenses();

    List<Expense> getExpensesByCategory(String category);

    void deleteExpense(Long id);
}