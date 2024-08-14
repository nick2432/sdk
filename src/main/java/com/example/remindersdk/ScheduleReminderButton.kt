package com.example.remindersdk

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.Toast

class ScheduleReminderButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Button(context, attrs, defStyleAttr) {

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ScheduleReminderButton, defStyleAttr, 0)

        val buttonText = typedArray.getString(R.styleable.ScheduleReminderButton_buttonText) ?: "Schedule Reminder"
        val buttonTextColor = typedArray.getColor(R.styleable.ScheduleReminderButton_buttonTextColor, currentTextColor)
        val buttonBackgroundColor = typedArray.getColor(R.styleable.ScheduleReminderButton_buttonBackgroundColor, -1)

        text = buttonText
        setTextColor(buttonTextColor)
        setBackgroundColor(buttonBackgroundColor)

        typedArray.recycle()

        setOnClickListener {
            // Placeholder for reminder scheduling logic
            Toast.makeText(context, "Reminder Scheduled!", Toast.LENGTH_SHORT).show()
        }
    }
}
