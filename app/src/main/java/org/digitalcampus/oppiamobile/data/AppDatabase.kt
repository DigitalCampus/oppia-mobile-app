package org.digitalcampus.oppiamobile.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.digitalcampus.oppiamobile.data.course.db.dao.CourseDao
import org.digitalcampus.oppiamobile.data.course.db.entity.CourseEntity
import org.digitalcampus.oppiamobile.data.user.db.dao.UserDao
import org.digitalcampus.oppiamobile.data.user.db.entity.UserEntity
import org.digitalcampus.oppiamobile.data.utils.converters.DateConverters

@Database(entities = [
    CourseEntity::class,
    UserEntity::class,
], version = 1)
@TypeConverters(DateConverters::class)
abstract class AppDatabase : RoomDatabase(){

    abstract fun courseDao(): CourseDao
    abstract fun userDao(): UserDao

}