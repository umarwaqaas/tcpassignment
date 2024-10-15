/*
 * *
 *  * Created by Umer Waqas on 1/7/21 12:53 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 1/7/21 12:50 PM
 *
 */

package com.tcp.assignment.dao

import androidx.room.*
import com.tcp.assignment.entity.TasksStatus


@Dao
interface TasksStatusDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTaskStatus(taskStatus: TasksStatus)

    @Query("SELECT * FROM user_tasks_table where task_id == :taskId")
    fun  getSpecificTaskStatus(taskId: String): TasksStatus

    @Query("SELECT task_status FROM user_tasks_table where task_id == :taskId")
    fun  getTaskStatus(taskId: String): Int

}