package com.tcp.assignment.utils

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment


fun Fragment.toast(msg: String) {
    Toast.makeText(
        context,
        msg, Toast.LENGTH_LONG
    ).show()
}

fun Activity.toast(msg: String) {
    Toast.makeText(
        this,
        msg, Toast.LENGTH_LONG
    ).show()
}
