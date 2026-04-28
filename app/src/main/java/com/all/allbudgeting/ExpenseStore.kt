package com.all.allbudgeting

object ExpenseStore {

    private val expenses = mutableListOf<Expense>()

    fun add(expense: Expense) {
        expenses.add(expense)
    }

    fun getAll(): List<Expense> {
        return expenses
    }

    fun categoryTotals(): Map<String, Double> {
        return expenses.groupBy { it.categoryName }
            .mapValues { entry -> entry.value.sumOf { it.amount } }
    }

    fun clear() {
        expenses.clear()
    }
}