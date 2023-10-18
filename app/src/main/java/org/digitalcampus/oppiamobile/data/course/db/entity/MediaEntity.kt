package org.digitalcampus.oppiamobile.data.course.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "media",
    foreignKeys = [ForeignKey(
        entity = ActivityEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("activity_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class MediaEntity (
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "activity_id") val activityId : Long,

    @ColumnInfo(name = "digest") val digest : String,
    @ColumnInfo(name = "filename") val filename : String,
    @ColumnInfo(name = "download_url") val download_url : String,
    @ColumnInfo(name = "length") val length : Int,
    @ColumnInfo(name = "filesize") val filesize : Int,
)
