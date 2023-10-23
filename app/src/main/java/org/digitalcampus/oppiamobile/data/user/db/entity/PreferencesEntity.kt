package org.digitalcampus.oppiamobile.data.user.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/*
    This is used to save the local preferences for all the users logged in the device
    (independently of the SharedPreferences data store), to be able to fetch them back
    if a user logs out and in again (hence why it's not linked with the User table directly)
 */

@Entity(tableName = "user_preference")
data class PreferencesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") val id: Long,

    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "preference") val preference: String,
    @ColumnInfo(name = "value") val value: String,
)
