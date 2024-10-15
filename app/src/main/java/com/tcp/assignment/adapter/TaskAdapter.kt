package com.example.taskmanagerapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.tcp.assignment.AppController
import com.tcp.assignment.R
import com.tcp.assignment.databinding.EachTaskItemBinding
import com.tcp.assignment.responses.TasksItem
import com.tcp.assignment.ui.TaskDetailActivity
import com.tcp.assignment.utils.Utils

class   TaskAdapter(
    var context: Context
) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    var modelList: List<TasksItem?>? = null
//    init {
//        modelList = data
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val resID = R.layout.each_task_item
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), resID, parent, false)
        return ViewHolder(binding as EachTaskItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val   eachTask = modelList!![position]
        holder.binding.txtTaskTitle.text = eachTask?.title
        holder.binding.txtDueDate.text = Utils.getParsedDate(eachTask?.dueDate.toString())
        holder.binding.txtDayLeft.text = Utils.calculateDaysBetween(eachTask?.targetDate.toString(), eachTask?.dueDate.toString())
        val taskStatus = AppController.mCustomerDao?.getSpecificTaskStatus(eachTask?.id.toString())
        if(taskStatus == null){
            holder.binding.layoutDaysLeft.visibility = View.VISIBLE
            holder.binding.btnTaskStatus.visibility = View.GONE
        }else{
            holder.binding.layoutDaysLeft.visibility = View.GONE
            holder.binding.btnTaskStatus.visibility = View.VISIBLE

            if(taskStatus?.task_status == Utils.TASK_RESOLVED){
                holder.binding.btnTaskStatus.setImageResource(R.drawable.btn_resolved)
            }else{
                holder.binding.btnTaskStatus.setImageResource(R.drawable.btn_unresolved)
            }
        }


        holder.binding.taskCardView.setOnClickListener {
            val intent = Intent(context, TaskDetailActivity::class.java)
            val taskJsonObject = Gson().toJson(eachTask)
            intent.putExtra("taskObject", taskJsonObject)
            holder.binding.root.context.startActivity(intent)
        }
       // holder.binding.txtDayLeft.text = eachTask?.da


    }
    fun setData(data: List<TasksItem?>){
        modelList = data
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return modelList!!.size
    }

    class ViewHolder(val binding: EachTaskItemBinding ) : RecyclerView.ViewHolder(binding.root) {
    }



}
