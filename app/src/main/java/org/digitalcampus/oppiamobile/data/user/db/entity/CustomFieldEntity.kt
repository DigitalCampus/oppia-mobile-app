package org.digitalcampus.oppiamobile.data.user.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "custom_field")
data class CustomFieldEntity(
    @PrimaryKey
    @ColumnInfo(name = "key") val key: String,

    @ColumnInfo(name = "label") val label: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "help_text") val helpText: String?,
    @ColumnInfo(name = "visible_by") val visibleBy: String?,
    @ColumnInfo(name = "visible_value") val visibleValue: String?,
    @ColumnInfo(name = "orderby") val order: Int,
    @ColumnInfo(name = "required") val required: Boolean,
    @ColumnInfo(name = "collection_name") val collectionName: String?,
    @ColumnInfo(name = "collection_by") val collectionBy: String?,
)

@Entity(
    tableName = "user_custom_field",
    primaryKeys = ["field_key", "user_id"],
    foreignKeys = [
        ForeignKey(
            entity = CustomFieldEntity::class,
            parentColumns = arrayOf("key"),
            childColumns = arrayOf("field_key"),
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("user_id"),
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class UserCustomFieldEntity(
    @ColumnInfo(name = "user_id") val userId: Long,
    @ColumnInfo(name = "field_key") val fieldKey: String = "",

    @ColumnInfo(name = "value_str") val valueStr: String? = null,
    @ColumnInfo(name = "value_int") val valueInt: Int? = null,
    @ColumnInfo(name = "value_bool") val valueBool: Boolean? = null,
    @ColumnInfo(name = "value_float") val valueFloat: Float? = null,
)

data class UserCustomFields(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "id", // user_id
        entityColumn = "key", // field_key
    )
    val customFields: List<CustomFieldEntity>,
)
