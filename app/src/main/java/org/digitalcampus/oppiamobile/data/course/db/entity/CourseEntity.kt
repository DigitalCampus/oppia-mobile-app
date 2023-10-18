package org.digitalcampus.oppiamobile.data.course.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "course")
data class CourseEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,

    @ColumnInfo(name = "version_id") val versionID: Int,
    @ColumnInfo(name = "shortname") val shortname: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "image") val courseIconImage: String?,
    @ColumnInfo(name = "priority") val orderPriority: Int,
    @ColumnInfo(name = "restricted") val restricted: Boolean,
    @ColumnInfo(name = "sequencing") val sequencing: String,
)

@Entity(tableName = "course_lang",
    foreignKeys = [ForeignKey(
        entity = CourseEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("course_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class CourseLangEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "course_id") val courseId : Long,
    @ColumnInfo(name = "lang") val lang: String,
)
