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

        val btnAddExpense =
            findViewById<Button>(R.id.btnAddExpense)

        val btnGraph =
            findViewById<Button>(R.id.btnGraph)

        val btnProgress =
            findViewById<Button>(R.id.btnProgress)

        val btnBadges =
            findViewById<Button>(R.id.btnBadges)

        val btnGoals =
            findViewById<Button>(R.id.btnGoals)

        val navHome =
            findViewById<TextView>(R.id.navHome)

        val navAdd =
            findViewById<TextView>(R.id.navAdd)

        val navSummary =
            findViewById<TextView>(R.id.navSummary)

        // Goals Screen
        btnGoals.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    GoalActivity::class.java
                )
            )
        }

        // Add Expense Screen
        btnAddExpense.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    AddExpenseActivity::class.java
                )
            )
        }

        // Graph Screen
        btnGraph.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    GraphActivity::class.java
                )
            )
        }

        // Budget Progress Screen
        btnProgress.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    BudgetProgressActivity::class.java
                )
            )
        }

        // Badges Screen
        btnBadges.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    BadgeActivity::class.java
                )
            )
        }

        // Bottom Navigation
        navHome.setOnClickListener {
            loadDashboard()
        }

        navAdd.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    AddExpenseActivity::class.java
                )
            )
        }

        navSummary.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MonthlySummaryActivity::class.java
                )
            )
        }
    }

    override fun onResume() {
        super.onResume()
        loadDashboard()
    }

    private fun loadDashboard() {

        val tvExpenseList =
            findViewById<TextView>(R.id.tvExpenseList)

        val healthGauge =
            findViewById<ProgressBar>(R.id.healthGauge)

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

        healthGauge.progress =
            total.toInt().coerceAtMost(10000)
    }
}