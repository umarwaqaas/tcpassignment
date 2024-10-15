/*
 * *
 *  * Created by Umer Waqas on 1/7/21 12:49 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 12/30/20 4:43 PM
 *
 */

package com.tcp.assignment

import android.support.multidex.MultiDexApplication

import com.tcp.assignment.dao.TasksStatusDao
import com.tcp.assignment.db.AppDatabase

class AppController : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = AppDatabase.getDatabase(context = this)
        mCustomerDao = database?.customerDao()
    }




    companion object {
        private val TAG = AppController::class.java
                .simpleName
         var database: AppDatabase? = null
        var mCustomerDao: TasksStatusDao? = null

        @get:Synchronized
        var instance: AppController? = null
            private set
    }



}