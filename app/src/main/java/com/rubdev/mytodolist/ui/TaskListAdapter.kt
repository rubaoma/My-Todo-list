package com.rubdev.mytodolist.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rubdev.mytodolist.R
import com.rubdev.mytodolist.databinding.ItemTaskBinding
import com.rubdev.mytodolist.model.Task

class TaskListAdapter : ListAdapter<Task, TaskListAdapter.TaskViewHolder>(DiffCallback()) {

    var listenerEdit: (Task) -> Unit = {}
    var listenerDelete: (Task) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TaskViewHolder(
        private val binding: ItemTaskBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Task) {
            binding.apply {
                tvTitle.text = item.title
                tvDate.text = "${item.date} ${item.hours}"
                ivOption.setOnClickListener {
                    showPopUp(item)
                }
            }

        }

        private fun showPopUp(item: Task) {
            val ivMore = binding.ivOption
            val popMenu = PopupMenu(ivMore.context, ivMore)
            popMenu.apply {
                menuInflater.inflate(R.menu.popun_menu, popMenu.menu)
                setOnMenuItemClickListener { menu ->
                    when (menu.itemId) {
                        R.id.action_edit -> listenerEdit(item)
                        R.id.action_delete -> listenerDelete(item)
                    }
                    return@setOnMenuItemClickListener true
                }
                popMenu.show()
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id
}