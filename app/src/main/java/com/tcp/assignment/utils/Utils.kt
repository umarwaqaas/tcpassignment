package com.tcp.assignment.utils

import android.app.Activity
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.daewoo.cod.utils.StringUtils
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.TimeUnit

/**
 * Created by Umer Waqas on 27/09/2022.
 * Daewoo Express Bus Service Pakistan
 * umarwaqaas@gmail.com
 */
object Utils {
    const val DATE_MMMM_DD_YYYY_FORMAT = "MMM dd, yyyy"
    const val TASK_RESOLVED = 0
    const val TASK_UNRESOLVED = 1
    fun hideKeyBoard(activity: Activity) {
        try {
            val inputMethodManager = activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                activity.currentFocus!!.windowToken, 0)
        } catch (e: Exception) {
            Log.e("TAG", e.toString())
        }
    }
    fun getParsedDate(date: String): String {
        if(StringUtils.isEmpty(date)){
            return "Not Available"
        }
        date.let {
            try {
                val parser = SimpleDateFormat("yyyy-MM-dd")
                val formatter = SimpleDateFormat(DATE_MMMM_DD_YYYY_FORMAT)
                return formatter.format(parser.parse(it))
            } catch (e: Exception) {

            }
        }
        return date
    }
    fun calculateDaysBetween(startDate: String, endDate: String): String {
        // Check if either of the input strings is empty
        if (StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)) {
            println("One of the input dates is empty.")
            return "N/A"
        }

        // Define the date format
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        return try {
            val start = dateFormat.parse(startDate)
            val end = dateFormat.parse(endDate)
            val differenceInMillis: Long = end.time - start.time

            // Convert milliseconds to days
            TimeUnit.MILLISECONDS.toDays(differenceInMillis).toString()

        } catch (e: Exception) {
            println("Error parsing dates: ${e.message}")
            "N/A"
        }
    }
}