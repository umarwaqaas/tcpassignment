package com.sama.health_life.network


import com.tcp.assignment.responses.TaskResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET(".")
     fun getTaskKey(): Call<TaskResponse>


}
