package com.tcp.assignment.responses

import com.google.gson.annotations.SerializedName

data class TaskResponse(

	@field:SerializedName("tasks")
	val tasks: List<TasksItem?>? = null
)

data class TasksItem(

	@field:SerializedName("Description")
	val description: String? = null,

	@field:SerializedName("TargetDate")
	val targetDate: String? = null,

	@field:SerializedName("Priority")
	val priority: Int? = null,

	@field:SerializedName("Title")
	val title: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("DueDate")
	val dueDate: String? = null
)
