package com.all.allbudgeting

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MonthlySummaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monthly_summary)

        val tvSummary = findViewById<TextView>(R.id.tvSummary)

        val expenses = ExpenseStore.getAll()

        if (expenses.isEmpty()) {
            tvSummary.text = "No expenses recorded yet"
            return
        }

        var total = 0.0
        var output = "MONTHLY SUMMARY\n\n"

        val categoryMap = mutableMapOf<String, Double>()

        for (e in expenses) {
            total += e.amount
            categoryMap[e.categoryName] =
                categoryMap.getOrDefault(e.categoryName, 0.0) + e.amount
        }

        output += "Total Spent: R$total\n\n"
        output += "By Category:\n"

        for ((cat, amount) in categoryMap) {
            output += "$cat = R$amount\n"
        }

        tvSummary.text = output
    }
}