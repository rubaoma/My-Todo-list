package com.rubdev.mytodolist.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.rubdev.mytodolist.databinding.ActivityAddTaskBinding
import com.rubdev.mytodolist.datasource.TaskDataSource
import com.rubdev.mytodolist.extensions.format
import com.rubdev.mytodolist.extensions.text
import com.rubdev.mytodolist.model.Task
import java.util.*

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra(TASK_ID)) {
            val taskId = intent.getIntExtra(TASK_ID, 0)
            TaskDataSource.findById(taskId)?.let {
                binding.tilTitle.text = it.title
                binding.tilDate.text = it.date
                binding.tilHour.text = it.hours
            }
        }
        insertListener()
    }

    private fun insertListener() {
        binding.apply {
            tilDate.editText?.setOnClickListener {
                val datePicker = MaterialDatePicker.Builder.datePicker().build()
                datePicker.apply {
                    addOnPositiveButtonClickListener {
                        val timeZone = TimeZone.getDefault()
                        val offset = timeZone.getOffset(Date().time) * -1
                        binding.tilDate.text = Date(it + offset).format()
                    }
                    show(supportFragmentManager, "DATE_PICKET_TAG")
                }
            }
            tilHour.editText?.setOnClickListener {
                val timePicker = MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .build()
                timePicker.apply {
                    addOnPositiveButtonClickListener {
                        val minute =
                            if (timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute
                        val hour =
                            if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour
                        binding.tilHour.text = "$hour:$minute"
                    }
                    show(supportFragmentManager, "TIME_PICKER_TAG")
                }
            }
            btnNewTask.setOnClickListener {
                val task = Task(
                    id = intent.getIntExtra(TASK_ID, 0),
                    title = binding.tilTitle.text,
                    date = binding.tilDate.text,
                    hours = binding.tilHour.text
                )
                TaskDataSource.insertTask(task)
                setResult(Activity.RESULT_OK)
                finish()
                Log.e("TAG", "InsertListner()," + TaskDataSource.getList())
            }
            btnCancel.setOnClickListener {
                finish()
            }
        }

    }

    companion object {
        const val TASK_ID = "task_id"
    }
}