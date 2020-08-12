package me.jerryhanks.todo.core.data.db

import androidx.room.TypeConverter
import java.time.LocalDateTime

/**
 * @author jerry on 07/08/2018
 * @project VelaBank
 **/
object DateConverter {

    @TypeConverter
    @JvmStatic
    fun toOffsetDateTime(value: String?): LocalDateTime? {
        return value?.let {
            LocalDateTime.parse(it)
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromOffsetDateTime(date: LocalDateTime?): String? {
        return date.toString()
    }
}