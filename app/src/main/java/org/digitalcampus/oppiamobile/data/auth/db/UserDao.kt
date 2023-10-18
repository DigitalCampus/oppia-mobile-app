package org.digitalcampus.oppiamobile.data.auth.db

import androidx.room.Dao
import androidx.room.Query
import org.digitalcampus.oppiamobile.data.config.db.BaseDao

@Dao
interface UserDao /*: BaseDao<UserEntity>*/ {

    @Query("SELECT * from user")
    fun getUsers(): List<UserEntity>

}