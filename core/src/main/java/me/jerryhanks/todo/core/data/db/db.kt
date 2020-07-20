package me.jerryhanks.todo.core.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase


/**
 * @author jerry on 17/07/2020
 * for Todo
 **/

@Database(entities = [Todo::class], version = 1, exportSchema = true)
abstract class TodoDB : RoomDatabase() {

    companion object {
        private const val DB_NAME = "todo.db"

        @Volatile
        private var INSTANCE: TodoDB? = null

        fun getInstance(context: Context, useInMemory: Boolean = false): TodoDB {

            val dbBuilder = if (useInMemory) {

                Room.inMemoryDatabaseBuilder(context, TodoDB::class.java).allowMainThreadQueries()
            } else {
                Room.databaseBuilder(
                    context,
                    TodoDB::class.java,
                    DB_NAME
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        //Db created
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