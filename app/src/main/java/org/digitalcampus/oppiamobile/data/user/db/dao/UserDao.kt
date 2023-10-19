package org.digitalcampus.oppiamobile.data.user.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.digitalcampus.oppiamobile.data.config.db.BaseDao
import org.digitalcampus.oppiamobile.data.user.db.entity.UserEntity

@Dao
interface UserDao : BaseDao<UserEntity> {

    @Query("SELECT * FROM user WHERE username = :username")
    fun getByUsername(username: String): UserEntity
}