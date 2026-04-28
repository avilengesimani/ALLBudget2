package com.all.allbudgeting

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAddExpense = findViewById<Button>(R.id.btnAddExpense)

        val navHome = findViewById<TextView>(R.id.navHome)
        val navAdd = findViewById<TextView>(R.id.navAdd)
        val navSummary = findViewById<TextView>(R.id.navSummary)

        // Go to Add Expense screen
        btnAddExpense.setOnClickListener {
            startActivity(Intent(this, AddExpenseActivity::class.java))
        }

        // Bottom navigation
        navHome.setOnClickListener {
            loadDashboard()
        }

        navAdd.setOnClickListener {
            startActivity(Intent(this, AddExpenseActivity::class.java))
        }

        navSummary.setOnClickListener {
            startActivity(Intent(this, MonthlySummaryActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        loadDashboard()
    }

    private fun loadDashboard() {

        val tvExpenseList = findViewById<TextView>(R.id.tvExpenseList)
        val healthGauge = findViewById<ProgressBar>(R.id.healthGauge)

        val expenses = ExpenseStore.getAll()

        if (expenses.isEmpty()) {
            tvExpenseList.text = "No expenses yet"
            healthGauge.progress = 0
            return
        }

        var text = ""
        var total = 0.0

        for (e in expenses) {
            text += "${e.date} | ${e.categoryName}: R${e.amount}\n"
            total += e.amount
        }

        text += "\nCATEGORY TOTALS:\n"

        val totals = ExpenseStore.categoryTotals()
        for ((cat, amount) in totals) {
            text += "$cat = R$amount\n"
        }

        tvExpenseList.text = text
        healthGauge.progress = total.toInt()
    }
}