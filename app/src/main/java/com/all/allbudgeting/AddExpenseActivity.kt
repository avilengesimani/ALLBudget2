package com.all.allbudgeting

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddExpenseActivity : AppCompatActivity() {

    private val REQUEST_IMAGE_CAPTURE = 1

    private lateinit var etAmount: EditText
    private lateinit var etDescription: EditText
    private lateinit var etDate: EditText
    private lateinit var ivReceipt: ImageView
    private lateinit var btnCapturePhoto: Button
    private lateinit var btnSaveExpense: Button
    private lateinit var spCategory: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // IMPORTANT - LOAD THE XML LAYOUT
        setContentView(R.layout.activity_add_expense)

        etDate = findViewById(R.id.etDate)
        etAmount = findViewById(R.id.etAmount)
        etDescription = findViewById(R.id.etDescription)
        ivReceipt = findViewById(R.id.ivReceipt)
        btnCapturePhoto = findViewById(R.id.btnCapturePhoto)
        btnSaveExpense = findViewById(R.id.btnSaveExpense)
        spCategory = findViewById(R.id.spCategory)

        // Category dropdown
        val categories = arrayOf(
            "Food",
            "Transport",
            "Rent",
            "Entertainment",
            "Other"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            categories
        )

        spCategory.adapter = adapter

        // Camera button
        btnCapturePhoto.setOnClickListener {
            try {
                val intent =
                    Intent(MediaStore.ACTION_IMAGE_CAPTURE)

                startActivityForResult(
                    intent,
                    REQUEST_IMAGE_CAPTURE
                )

            } catch (e: Exception) {

                Toast.makeText(
                    this,
                    "Camera not available",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Save Expense
        btnSaveExpense.setOnClickListener {

            val amount =
                etAmount.text.toString().toDoubleOrNull()

            val description =
                etDescription.text.toString()

            val date =
                etDate.text.toString()

            val category =
                spCategory.selectedItem.toString()

            if (amount == null) {

                Toast.makeText(
                    this,
                    "Enter a valid amount",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (date.isBlank()) {

                Toast.makeText(
                    this,
                    "Enter a date",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val expense = Expense(
                amount = amount,
                date = date,
                startTime = "09:00",
                endTime = "09:15",
                description = description,
                categoryName = category,
                photoUri = null
            )

            ExpenseStore.add(expense)

            Toast.makeText(
                this,
                "Expense Saved!",
                Toast.LENGTH_SHORT
            ).show()

            finish()
        }
    }

    @Deprecated("Deprecated but acceptable for this project")
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(
            requestCode,
            resultCode,
            data
        )

        if (
            requestCode == REQUEST_IMAGE_CAPTURE &&
            resultCode == Activity.RESULT_OK
        ) {

            val imageBitmap =
                data?.extras?.get("data") as? Bitmap

            imageBitmap?.let {
                ivReceipt.setImageBitmap(it)
            }
        }
    }
}