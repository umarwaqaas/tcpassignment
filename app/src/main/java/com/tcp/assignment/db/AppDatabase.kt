package com.tcp.assignment.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tcp.assignment.dao.TasksStatusDao
import com.tcp.assignment.entity.TasksStatus


@Database(
    entities = [TasksStatus::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TasksStatusDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()

        fun getDatabase(context: Context): AppDatabase =
            INSTANCE ?: synchronized(LOCK) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "tcp_consignment.db"
            ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }
}