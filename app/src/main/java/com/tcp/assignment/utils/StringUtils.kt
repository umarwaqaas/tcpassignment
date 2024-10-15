/*
 * *
 *  * Created by Umer Waqas on 1/7/21 12:50 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *  * Last modified 1/6/21 3:51 PM
 *
 */

package com.daewoo.cod.utils

import android.text.TextUtils

/**
 * Created by Umer Waqas on 06/02/2019.
 */
object StringUtils {
    fun String?.isEmptyOrNull() : Boolean{
        return !this.isNullOrEmpty() && !this.equals("null",ignoreCase = true)
    }
    fun isEmpty(text: String?): Boolean {
        return if (text == null || text.equals("null", ignoreCase = true)) {
            true
        } else TextUtils.isEmpty(text.trim { it <= ' ' })
    }

    fun isEmpty(text: CharSequence?): Boolean {
        return if (text == null) {
            true
        } else isEmpty(text.toString())
    }
}