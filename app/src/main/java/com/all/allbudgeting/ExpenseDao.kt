package com.all.allbudgeting

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExpenseDao {
    @Insert
    fun insertExpense(expense: Expense)

    @Insert
    fun insertCategory(category: Category)

    @Query("SELECT * FROM categories")
    fun getAllCategories(): List<Category>

    @Query("SELECT * FROM expenses")
    fun getAllExpenses(): List<Expense>

    // Category totals for the analytics requirement
    @Query("SELECT categoryName, SUM(amount) as total FROM expenses GROUP BY categoryName")
    fun getCategoryTotals(): List<CategoryTotal>
}

data class CategoryTotal(val categoryName: String, val total: Double)