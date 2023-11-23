package org.digitalcampus.oppiamobile.data.user.db.dao

import androidx.room.Dao
import androidx.room.Query
import org.digitalcampus.oppiamobile.data.config.db.BaseDao
import org.digitalcampus.oppiamobile.data.user.db.entity.CustomFieldEntity

@Dao
interface CustomFieldDao : BaseDao<CustomFieldEntity> {

    @Query("SELECT * FROM custom_field ORDER BY orderby")
    fun getAll(): List<CustomFieldEntity>
}
