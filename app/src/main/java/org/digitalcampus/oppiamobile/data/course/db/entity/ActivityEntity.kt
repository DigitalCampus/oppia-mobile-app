package org.digitalcampus.oppiamobile.data.course.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "activity",
    foreignKeys = [
        ForeignKey(
            entity = SectionEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("section_id"),
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class ActivityEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "section_id") val sectionId: Long,

    @ColumnInfo(name = "digest") val digest: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "location") val location: String,

    @ColumnInfo(name = "is_baseline") val is_baseline: Boolean,
    @ColumnInfo(name = "orderno") val order: Int,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "start_date") val start_date: Date?,
    @ColumnInfo(name = "end_date") val end_date: Date?,
    @ColumnInfo(name = "word_count") val word_count: Int,
)
