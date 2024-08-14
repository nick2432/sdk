package com.example.remindersdk

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.util.Calendar

class DateTimePickerComponent @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val dateButton: Button
    private val timeButton: Button
    private val selectedDateTextView: TextView
    private val selectedTimeTextView: TextView

    private var selectedDate: Calendar = Calendar.getInstance()

    init {
        orientation = VERTICAL

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.DateTimePickerComponent, defStyleAttr, 0)

        val dateButtonText = typedArray.getString(R.styleable.DateTimePickerComponent_dateButtonText) ?: "Select Date"
        val timeButtonText = typedArray.getString(R.styleable.DateTimePickerComponent_timeButtonText) ?: "Select Time"
        val selectedTextColor = typedArray.getColor(R.styleable.DateTimePickerComponent_selectedTextColor, -1)
        val buttonBackgroundColor = typedArray.getColor(R.styleable.DateTimePickerComponent_timeButtonBackgroundColor, -1)

        dateButton = Button(context).apply {
            text = dateButtonText
            setBackgroundColor(buttonBackgroundColor)
            setOnClickListener { showDatePicker() }
        }

        timeButton = Button(context).apply {
            text = timeButtonText
            setBackgroundColor(buttonBackgroundColor)
            setOnClickListener { showTimePicker() }
        }

        selectedDateTextView = TextView(context).apply {
            setTextColor(selectedTextColor)
        }
        selectedTimeTextView = TextView(context).apply {
            setTextColor(selectedTextColor)
        }

        typedArray.recycle()

        addView(dateButton)
        addView(selectedDateTextView)
        addView(timeButton)
        addView(selectedTimeTextView)
    }

    private fun showDatePicker() {
        DatePickerDialog(context, { _, year, month, dayOfMonth ->
            selectedDate.set(year, month, dayOfMonth)
            selectedDateTextView.text = "Selected Date: $dayOfMonth/${month + 1}/$year"
        }, selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH), selectedDate.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun showTimePicker() {
        TimePickerDialog(context, { _, hourOfDay, minute ->
            selectedDate.set(Calendar.HOUR_OF_DAY, hourOfDay)
            selectedDate.set(Calendar.MINUTE, minute)
            selectedTimeTextView.text = "Selected Time: $hourOfDay:$minute"
        }, selectedDate.get(Calendar.HOUR_OF_DAY), selectedDate.get(Calendar.MINUTE), true).show()
    }

    fun getSelectedDateTime(): Calendar = selectedDate

    fun setDateButtonText(text: String) {
        dateButton.text = text
    }

    fun setTimeButtonText(text: String) {
        timeButton.text = text
    }

    fun setSelectedTextColor(color: Int) {
        selectedDateTextView.setTextColor(color)
        selectedTimeTextView.setTextColor(color)
    }

    fun setButtonBackgroundColor(color: Int) {
        dateButton.setBackgroundColor(color)
        timeButton.setBackgroundColor(color)
    }
}
