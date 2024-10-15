package com.tcp.assignment.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sama.health_life.network.ApiClient
import com.tcp.assignment.responses.TaskResponse
import com.tcp.assignment.responses.TasksItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskRepository() {
//    private val taskApiService = ApiClient.getApiInterface()
val mAllTasks = MutableLiveData<List<TasksItem?>>()
//    val _taskList = MutableLiveData<List<TasksItem>>()
//
////    val taskList: MutableLiveData<List<TasksItem>>
////        get() = _taskList
//
//    suspend  fun fetchTasks() {
//        taskApiService.getTaskKey().enqueue(object : Callback<TaskResponse> {
//            override fun onResponse(call: Call<TaskResponse>, response: Response<TaskResponse>) {
//                Log.e("TAG", "onResponse: "+response.body()?.tasks )
//              //  _taskList.postValue(response.body()?.tasks as List<TasksItem>?)
//            }
//
//            override fun onFailure(call: Call<TaskResponse>, t: Throwable) {
//                Log.e("TAG", "onResponse: "+t.message )
//            }
//        })
//    }


    private val taskApiService = ApiClient.getApiInterface()


      fun fetchTasks() {
        taskApiService.getTaskKey().enqueue(object : Callback<TaskResponse> {


            override fun onResponse(call: Call<TaskResponse>, response: Response<TaskResponse>) {
                Log.e("TAG", "onResponse: "+response.body()?.tasks )
                val tasks = response.body()?.tasks
                if (tasks != null) {
                    mAllTasks.postValue(tasks)
                }

            }

            override fun onFailure(call: Call<TaskResponse>, t: Throwable) {
                //TODO("Not yet implemented")
                Log.e("TAG", "onResponse: "+t.message )
            }
        })
    }
}
