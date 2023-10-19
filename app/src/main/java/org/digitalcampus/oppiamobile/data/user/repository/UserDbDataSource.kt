package org.digitalcampus.oppiamobile.data.user.repository

import org.digitalcampus.oppiamobile.data.user.db.dao.UserDao
import org.digitalcampus.oppiamobile.data.user.db.entity.UserEntity
import org.digitalcampus.oppiamobile.domain.model.User
import javax.inject.Inject

class UserDbDataSource @Inject constructor(private val userDao: UserDao) {

    suspend fun updateUser(user: UserEntity) = userDao.update(user)

    suspend fun getByUsername(username: String) = userDao.getByUsername(username)

}