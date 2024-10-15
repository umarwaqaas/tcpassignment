package com.tcp.assignment.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.taskmanagerapp.TaskAdapter
import com.tcp.assignment.viewmodels.TaskViewModel
import com.tcp.assignment.databinding.ActivityTasksListBinding
import com.tcp.assignment.responses.TasksItem

class TaskListActivity : AppCompatActivity() {

    lateinit var customerBillAdapter: TaskAdapter
    lateinit var binding: ActivityTasksListBinding
    var taskViewModel: TaskViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTasksListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        taskViewModel = ViewModelProvider(this@TaskListActivity).get(TaskViewModel::class.java);
        setAdapter()
        taskViewModel?._filteredTaskList?.observe(this, Observer { tasks ->
            tasks?.let {
                 setAdapterData(tasks)
                if(tasks?.size!! > 0){
                    binding.noDataFoundLayout.visibility = View.GONE
                }else{
                    binding.noDataFoundLayout.visibility = View.VISIBLE
                }

            }
        })

        taskViewModel?.fetchTasks()
        taskViewModel?.titleDisplayText?.observeForever {
            binding.txtCurrentDateTitle.text = it
        }

        taskViewModel?.setSelectedDate()
        binding.btnNext.setOnClickListener {
            taskViewModel?.nextDate()
        }
        binding.btnPrevious.setOnClickListener {
            taskViewModel?.previousDate()
        }


    }


    private fun setAdapter(){
        binding.rvTaskList.visibility = View.VISIBLE
        customerBillAdapter = TaskAdapter(this@TaskListActivity )
        binding.rvTaskList.adapter = customerBillAdapter
    }
    private fun setAdapterData(tasksList : List<TasksItem?>){
      customerBillAdapter?.setData(tasksList)
    }


}