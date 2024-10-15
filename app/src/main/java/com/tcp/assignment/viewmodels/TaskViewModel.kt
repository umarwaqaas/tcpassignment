package com.tcp.assignment.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tcp.assignment.repository.TaskRepository
import com.tcp.assignment.responses.TasksItem
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class TaskViewModel : ViewModel() {

    private val repository = TaskRepository()
    private var allTasks: List<TasksItem> = emptyList()
    val _filteredTaskList = MutableLiveData<List<TasksItem?>>()
    private val filteredTaskList: LiveData<List<TasksItem?>> get() = _filteredTaskList
    private var selectedDate = Date()
    private val dateFormatter = SimpleDateFormat("yyyy-MM-dd")
     var titleDisplayText: MutableLiveData<String> = MutableLiveData()

    fun fetchTasks() {
        viewModelScope.launch {
            repository.fetchTasks()
        }

        repository.mAllTasks.observeForever { tasks ->
            allTasks = tasks as List<TasksItem>
            filterTasksByDate()
        }
    }

    fun setSelectedDate() {
        filterTasksByDate()  // Filter tasks whenever the date changes
    }

    private fun filterTasksByDate() {
        val formattedDate = dateFormatter.format(selectedDate)
        val filteredTasks = if (formattedDate != null) {
            allTasks.filter { task -> task.targetDate == formattedDate }
        } else {
            allTasks
        }

        _filteredTaskList?.value?.sortedWith(compareByDescending<TasksItem?> { it?.priority }.thenBy { it?.dueDate })
        _filteredTaskList.postValue(
            filteredTasks.ifEmpty { emptyList() }
        )
        titleDisplayText.postValue( getDisplayDateText())
    }
    // Move to the Next date
    fun nextDate() {
        val calendar = Calendar.getInstance()
        calendar.time = selectedDate
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        selectedDate = calendar.time
        setSelectedDate()
    }

    // Move to the previous date
    fun previousDate() {
        val calendar = Calendar.getInstance()
        calendar.time = selectedDate
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        selectedDate = calendar.time
        setSelectedDate()
    }
    private fun getDisplayDateText(): String {
        val today = Date()

        val calendar = Calendar.getInstance()
        calendar.time = today

        val tomorrow = Calendar.getInstance().apply {
            time = today
            add(Calendar.DAY_OF_YEAR, 1)
        }.time

        val yesterday = Calendar.getInstance().apply {
            time = today
            add(Calendar.DAY_OF_YEAR, -1)
        }.time

        return when {
            selectedDate.isSameDayAs(today) -> "Today"
            selectedDate.isSameDayAs(yesterday) -> "Yesterday"
            selectedDate.isSameDayAs(tomorrow) -> "Tomorrow"
            else -> dateFormatter.format(selectedDate)
        }
    }
    private fun Date.isSameDayAs(other: Date): Boolean {
        val thisCalendar = Calendar.getInstance().apply { time = this@isSameDayAs }
        val otherCalendar = Calendar.getInstance().apply { time = other }

        return thisCalendar.get(Calendar.YEAR) == otherCalendar.get(Calendar.YEAR) &&
                thisCalendar.get(Calendar.DAY_OF_YEAR) == otherCalendar.get(Calendar.DAY_OF_YEAR)
    }
}

