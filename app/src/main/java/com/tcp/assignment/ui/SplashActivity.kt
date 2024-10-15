/*
 * *
 *  * Created by Umer Waqas on 1/7/21 12:52 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 12/30/20 4:48 PM
 *
 */

package com.tcp.assignment.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.tcp.assignment.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this@SplashActivity,
                        TaskListActivity::class.java))
            finish()
        }, 1000)
    }
}