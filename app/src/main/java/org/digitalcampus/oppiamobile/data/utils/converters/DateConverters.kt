package org.digitalcampus.oppiamobile.data.utils.converters

import androidx.room.TypeConverter
import java.time.Instant
import java.util.Date

public class DateConverters {

    @TypeConverter
    fun fromTimestamp(value : Long?): Date? {
        return value?.let { Date(value) }
    }

    @TypeConverter
    fun dateToTimestamp(date : Date? ): Long? {
        return date?.getTime()
    }
}