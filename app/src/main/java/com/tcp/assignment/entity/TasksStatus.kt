/*
 * *
 *  * Created by Umer Waqas on 1/7/21 12:52 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 12/31/20 12:37 PM
 *
 */

package com.tcp.assignment.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Umer Waqas on 27/08/2021.
 * Daewoo Express Bus Service Pakistan
 * umarwaqaas@gmail.com
 */
@Entity(tableName = "user_tasks_table")
data class TasksStatus(


    @ColumnInfo(name = "task_id") var task_id: String,
    @ColumnInfo(name = "task_status") var task_status: Int,
    @ColumnInfo(name = "task_user_comments") var task_comments: String,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}


