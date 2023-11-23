package org.digitalcampus.oppiamobile.data.user.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.digitalcampus.oppiamobile.utils.CryptoUtils
import java.util.Calendar
import java.util.Date

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,

    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "api_key") val apiKey: String = "",
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "last_login_date") val lastLoginDate: Date = Calendar.getInstance().time,
    @ColumnInfo(name = "login_count") val loginCount: Int = 0,
    @ColumnInfo(name = "points") val points: Int = 0,
    @ColumnInfo(name = "badges") val badges: Int = 0,
    @ColumnInfo(name = "offline_register") val offlineRegister: Boolean = false,

)

val UserEntity.passwordEncrypted: String
    get() = CryptoUtils.encryptLocalPassword(password)
