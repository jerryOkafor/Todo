package me.jerryhanks.todo.core.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import me.jerryhanks.todo.core.data.db.daos.TaskDao
import me.jerryhanks.todo.core.data.db.entities.Task
import me.jerryhanks.todo.core.data.db.entities.TasksList

/**
 * @author jerry on 17/07/2020
 * for Todo
 **/

@Database(entities = [TasksList::class, Task::class], version = 1, exportSchema = true)
@TypeConverters(DateConverter::class)
abstract class TasksDB : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        private const val DB_NAME = "tasks.db"

        @Volatile
        private var INSTANCE: TasksDB? = null

        fun getInstance(context: Context, useInMemory: Boolean = false): TasksDB {
            val dbBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, TasksDB::class.java).allowMainThreadQueries()
            } else {
                Room.databaseBuilder(
                    context,
                    TasksDB::class.java,
                    DB_NAME
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Db created
                    }
                }).fallbackToDestructiveMigration()
            }

            return synchronized(this) {
                INSTANCE ?: dbBuilder.build()
                    .also { INSTANCE = it }
            }
        }
    }
}