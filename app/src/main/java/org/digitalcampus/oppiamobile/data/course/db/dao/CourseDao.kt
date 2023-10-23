package org.digitalcampus.oppiamobile.data.course.db.dao

import androidx.room.Dao
import androidx.room.Query
import org.digitalcampus.oppiamobile.data.config.db.BaseDao
import org.digitalcampus.oppiamobile.data.course.db.entity.CourseEntity

@Dao
interface CourseDao : BaseDao<CourseEntity> {

    @Query("SELECT * FROM course")
    fun getAll(): List<CourseEntity>

    @Query("SELECT * FROM course WHERE shortname = :shortname")
    fun getByShortname(shortname: String): CourseEntity

    @Query("SELECT EXISTS(SELECT * FROM course WHERE shortname = :shortname)")
    fun isInstalled(shortname: String): Boolean
}