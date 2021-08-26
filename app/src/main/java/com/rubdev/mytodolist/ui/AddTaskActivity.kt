package com.rubdev.mytodolist.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.rubdev.mytodolist.TaskApplication
import com.rubdev.mytodolist.TaskViewModel
import com.rubdev.mytodolist.TaskViewModelFactory
import com.rubdev.mytodolist.databinding.ActivityAddTaskBinding
import com.rubdev.mytodolist.extensions.format
import com.rubdev.mytodolist.extensions.text
import com.rubdev.mytodolist.model.Task
import java.util.*

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModelFactory((application as TaskApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayTaskValuesForEdit()
        insertListener()
    }

    private fun displayTaskValuesForEdit(){
        if (intent.hasExtra(TASK_ID)) {
            val taskId = intent.getIntExtra(TASK_ID, 0)
            taskViewModel.allTasks.observe(this) { tasks ->
                tasks.forEach {
                    if (it.id == taskId)
                        binding.apply {
                            tilTitle.text = it.title
                            tilDate.text = it.date
                            tilHour.text = it.hours
                        }
                }
            }
        }
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
                if (intent.hasExtra(TASK_ID)) {
                    val taskId = intent.getIntExtra(TASK_ID, 0)
                    taskViewModel.allTasks.observe(this@AddTaskActivity) { tasks ->
                        tasks.forEach {
                            if (it.id == taskId) {
                                val updateTask = Task(
                                id = taskId,
                                title = binding.tilTitle.text,
                                date = binding.tilDate.text,
                                hours = binding.tilHour.text
                                )
                                taskViewModel.delete(it)
                                taskViewModel.insert(updateTask)
                            }
                        }
                    }
                }
                val replyIntent = Intent()
                val task = Task(
                    id = intent.getIntExtra(TASK_ID, 0),
                    title = binding.tilTitle.text,
                    date = binding.tilDate.text,
                    hours = binding.tilHour.text
                )
                replyIntent.putExtra(TASK_ID, task)
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
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