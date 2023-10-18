package org.digitalcampus.oppiamobile.data.course.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.digitalcampus.oppiamobile.data.user.db.entity.UserEntity

@Entity(tableName = "course_section",
    foreignKeys = [ForeignKey(
        entity = CourseEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("course_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class SectionEntity (
    @PrimaryKey(autoGenerate = true) val id: Long,

    @ColumnInfo(name = "course_id") val courseId : Long,
    @ColumnInfo(name = "title") val title : String,
    @ColumnInfo(name = "orderno") val order : String,
    @ColumnInfo(name = "image") val image : String,
    @ColumnInfo(name = "password") val password : String,
)

@Entity(tableName = "unlocked_section",
    foreignKeys = [
        ForeignKey(
            entity = SectionEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("section_id"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("user_id"),
            onDelete = ForeignKey.CASCADE
        ),
    ]
)
data class UnlockedSectionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,

    @ColumnInfo(name = "user_id") val userId : Long,
    @ColumnInfo(name = "section_id") val sectionId : Long,
    @ColumnInfo(name = "password") val password : String,
)
