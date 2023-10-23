package org.digitalcampus.oppiamobile.data.user.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import org.digitalcampus.oppiamobile.data.config.db.BaseDao
import org.digitalcampus.oppiamobile.data.user.db.entity.CustomFieldEntity
import org.digitalcampus.oppiamobile.data.user.db.entity.UserCustomFields

@Dao
interface CustomFieldDao : BaseDao<CustomFieldEntity> {

    @Query("SELECT * FROM custom_field ORDER BY orderby")
    fun getAll(): List<CustomFieldEntity>

    @Transaction
    @Query("SELECT * FROM user WHERE id = :userId")
    fun getUserCustomFields(userId: Int): UserCustomFields
}
