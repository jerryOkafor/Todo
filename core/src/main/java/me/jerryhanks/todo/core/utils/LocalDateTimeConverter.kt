package me.jerryhanks.todo.core.utils

import com.google.gson.*
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


/**
 * @author jerry on 13/08/2020
 * for Todo
 **/

class LocalDateTimeConverter : JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

    override fun serialize(
        src: LocalDateTime?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        return JsonPrimitive(src.toString())
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalDateTime {
        return json?.asString?.let { LocalDateTime.parse(it, DateTimeFormatter.ISO_DATE_TIME) }
            ?: LocalDateTime.now()
    }
}
