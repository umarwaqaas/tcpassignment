package com.tcp.assignment.ui

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.tcp.assignment.AppController
import com.tcp.assignment.R
import com.tcp.assignment.databinding.ActivityTaskDetailBinding
import com.tcp.assignment.databinding.DialogAskTaskCommentsBinding
import com.tcp.assignment.entity.TasksStatus
import com.tcp.assignment.responses.TasksItem
import com.tcp.assignment.utils.Utils
import com.tcp.assignment.utils.toast

class TaskDetailActivity : AppCompatActivity() {

    private var taskItem: TasksItem? = null
    lateinit var binding: ActivityTaskDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        receiveIntent()
        setUI()
    }

    private fun setUI() {
        binding.btnResolve.setOnClickListener {
            showCommentsDialog(Utils.TASK_RESOLVED)
        }
        binding.btnUnResolved.setOnClickListener {
            showCommentsDialog(Utils.TASK_UNRESOLVED)
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
    private fun receiveIntent() {
        val deliveryJsonObject = intent.getStringExtra("taskObject")
        taskItem = Gson().fromJson(deliveryJsonObject, TasksItem::class.java)
        if(taskItem!=null){
            binding.txtTaskTitle.text = taskItem?.title
            binding.txtTaskDescription.text = taskItem?.description
            binding.txtDueDate.text = Utils.getParsedDate(taskItem?.dueDate.toString())
            binding.txtDayLeft.text = Utils.calculateDaysBetween(taskItem?.targetDate.toString(), taskItem?.dueDate.toString())
            getTaskStatus(taskItem?.id.toString())
        }

    }
    private  fun showCommentsDialog(taskStatus: Int ) {

        val dialogBinding: DialogAskTaskCommentsBinding = DialogAskTaskCommentsBinding.inflate(layoutInflater)
       val mydialog = Dialog(this@TaskDetailActivity)
        mydialog.setContentView(dialogBinding.getRoot())
        mydialog.setCanceledOnTouchOutside(false)
        mydialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialogBinding.btnYes.setOnClickListener { v1 ->
            val comments= dialogBinding.edtComments.getText().toString()
            if(StringUtils.isEmpty(comments)){
                dialogBinding.errorMessage.visibility = View.VISIBLE
                return@setOnClickListener
            }else{
                dialogBinding.errorMessage.visibility = View.GONE
                addTaskStatus(taskItem?.id.toString(), taskStatus, comments)
                mydialog.dismiss()
            }


        }


        dialogBinding.btnNo.setOnClickListener { v13 ->
            addTaskStatus(taskItem?.id.toString(), taskStatus, "")
            mydialog.dismiss()
        }

        mydialog.show()
    }
    fun addTaskStatus(taskID: String, taskStatus: Int, taskComments: String) {
        val taskStatus = TasksStatus(taskID, taskStatus, taskComments)
        AppController.mCustomerDao?.insertTaskStatus(taskStatus)
        toast("Task Status Updated")
        getTaskStatus(taskID)
    }
    private fun getTaskStatus(taskID: String) {
        val taskStatus = AppController.mCustomerDao?.getSpecificTaskStatus(taskID)
        if(taskStatus == null){
            binding.taskStatusButtonControl.visibility = View.VISIBLE
            binding.imageTaskStatus.visibility = View.GONE
            binding.txtTaskStatus.text = "Unresolved"
            binding.txtTaskStatus.setTextColor(ContextCompat.getColor(this , R.color.darker_orange_color))
        }else{
            binding.taskStatusButtonControl.visibility = View.GONE
            binding.imageTaskStatus.visibility = View.VISIBLE
            if(taskStatus.task_status == Utils.TASK_RESOLVED){
                binding.imageTaskStatus.setImageResource(R.drawable.sign_resolved)
                binding.txtTaskStatus.text = "Resolved"
                binding.txtTaskStatus.setTextColor(ContextCompat.getColor(this , R.color.teal_color))
            }else{
                binding.imageTaskStatus.setImageResource(R.drawable.unresolved_sign)
                binding.txtTaskStatus.text = "Unresolved"
                binding.txtTaskStatus.setTextColor(ContextCompat.getColor(this , R.color.red_color))
            }

        }
    }


}