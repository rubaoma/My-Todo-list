package com.rubdev.mytodolist.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rubdev.mytodolist.databinding.ActivityMainBinding
import com.rubdev.mytodolist.viewmodel.TaskViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { TaskListAdapter() }
    private val taskViewModel: TaskViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTasks.adapter = adapter

        updateList()
        insertListeners()
    }

    private fun insertListeners() {
        binding.fab.setOnClickListener {
            startActivityForResult(
                Intent(
                    this,
                    AddTaskActivity::class.java),
                CREATE_NEW_TASK
            )
        }

        adapter.listenerEdit = {
            val intent = Intent(this, AddTaskActivity::class.java)
            intent.putExtra(AddTaskActivity.TASK_ID, it.id)
            startActivityForResult(intent, CREATE_NEW_TASK)

        }
        adapter.listenerDelete = { task ->
            taskViewModel.delete(task)
            updateList()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CREATE_NEW_TASK && resultCode == Activity.RESULT_OK) {
            updateList()
        }
    }

    private fun updateList() {
        taskViewModel.allTasks.observe(this) { tasks ->
            tasks.let { task ->
                binding.includeView.emptyState.visibility = if (task.isNullOrEmpty())
                    View.VISIBLE
                else
                    View.GONE
                adapter.submitList(task)
            }
        }
    }

    companion object {
        private const val CREATE_NEW_TASK = 1000
    }
}