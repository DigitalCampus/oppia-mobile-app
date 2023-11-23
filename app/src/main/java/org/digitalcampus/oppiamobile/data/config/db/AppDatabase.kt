package org.digitalcampus.oppiamobile.data.config.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.digitalcampus.oppiamobile.data.course.db.dao.CourseDao
import org.digitalcampus.oppiamobile.data.course.db.entity.ActivityEntity
import org.digitalcampus.oppiamobile.data.course.db.entity.CourseEntity
import org.digitalcampus.oppiamobile.data.course.db.entity.CourseLangEntity
import org.digitalcampus.oppiamobile.data.course.db.entity.MediaEntity
import org.digitalcampus.oppiamobile.data.course.db.entity.SectionEntity
import org.digitalcampus.oppiamobile.data.user.db.dao.CustomFieldDao
import org.digitalcampus.oppiamobile.data.user.db.dao.PreferencesDao
import org.digitalcampus.oppiamobile.data.user.db.dao.UserCustomFieldDao
import org.digitalcampus.oppiamobile.data.user.db.dao.UserDao
import org.digitalcampus.oppiamobile.data.user.db.entity.CustomFieldEntity
import org.digitalcampus.oppiamobile.data.user.db.entity.PreferencesEntity
import org.digitalcampus.oppiamobile.data.user.db.entity.UserCustomFieldEntity
import org.digitalcampus.oppiamobile.data.user.db.entity.UserEntity
import org.digitalcampus.oppiamobile.data.utils.converters.DateConverters

@Database(
    entities = [
        UserEntity::class,
        PreferencesEntity::class,
        CourseEntity::class,
        CourseLangEntity::class,
        SectionEntity::class,
        ActivityEntity::class,
        MediaEntity::class,
        CustomFieldEntity::class,
        UserCustomFieldEntity::class,
    ],
    version = 2,
    exportSchema = false,
)
@TypeConverters(DateConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun customFieldDao(): CustomFieldDao
    abstract fun userCustomFieldDao(): UserCustomFieldDao
    abstract fun preferencesDao(): PreferencesDao
    abstract fun courseDao(): CourseDao
}
