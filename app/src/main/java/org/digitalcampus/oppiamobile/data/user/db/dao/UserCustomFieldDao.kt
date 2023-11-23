package org.digitalcampus.oppiamobile.data.user.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import org.digitalcampus.oppiamobile.data.config.db.BaseDao
import org.digitalcampus.oppiamobile.data.user.db.entity.UserCustomFieldEntity

@Dao
interface UserCustomFieldDao : BaseDao<UserCustomFieldEntity> {
    @Transaction
    @Query("SELECT * FROM user_custom_field WHERE user_id = :userId")
    fun getUserCustomFields(userId: Long): UserCustomFieldEntity
}
