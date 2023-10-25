package org.digitalcampus.oppiamobile.data.user.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.digitalcampus.oppiamobile.utils.CryptoUtils
import java.util.Calendar
import java.util.Date

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,

    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "first_name") val first_name: String, // TODO COMENTAR Usamos camelCase?
    @ColumnInfo(name = "last_name") val last_name: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "api_key") val api_key: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "last_login_date") val last_login_date: Date = Calendar.getInstance().time,
    @ColumnInfo(name = "login_count") val login_count: Int,
    @ColumnInfo(name = "points") val points: Int,
    @ColumnInfo(name = "badges") val badges: Int,
    @ColumnInfo(name = "offline_register") val offline_register: Boolean,

)

val UserEntity.passwordEncrypted: String
    get() = CryptoUtils.encryptLocalPassword(password)
