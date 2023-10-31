package org.digitalcampus.oppiamobile.data.user.repository

import org.digitalcampus.oppiamobile.data.user.db.entity.UserEntity
import org.digitalcampus.oppiamobile.domain.model.User

fun UserEntity.toUser() = User(email, username, first_name, last_name, api_key)


// TODO COMENTAR  criterio de nombres de archivos: ficheros que no son clases? nombres de paquetes?