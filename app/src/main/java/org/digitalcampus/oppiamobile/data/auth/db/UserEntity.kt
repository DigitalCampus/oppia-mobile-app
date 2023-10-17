package org.digitalcampus.oppiamobile.data.auth.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val email: String,
    val username: String,
    val firstName: String,
    val lastName: String,
    val organisation: String,
    val jobTitle: String,
    val apiKey: String,
)
