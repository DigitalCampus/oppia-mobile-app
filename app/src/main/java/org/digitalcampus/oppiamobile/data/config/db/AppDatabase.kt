package org.digitalcampus.oppiamobile.data.config.db

import androidx.room.Database
import androidx.room.RoomDatabase
import org.digitalcampus.oppiamobile.data.auth.db.UserDao
import org.digitalcampus.oppiamobile.data.auth.db.UserEntity

@Database(entities = [UserEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}