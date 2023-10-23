package org.digitalcampus.oppiamobile.data.user.repository

import org.digitalcampus.oppiamobile.data.user.db.entity.UserEntity
import org.digitalcampus.oppiamobile.domain.model.User

fun UserEntity.toUser() = User(email, username, first_name, last_name, api_key)
