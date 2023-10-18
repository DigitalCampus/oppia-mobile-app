package org.digitalcampus.oppiamobile.data.user.db.repository

import org.digitalcampus.oppiamobile.data.auth.db.UserDao
import javax.inject.Inject

class UserDbDataSource @Inject constructor(val userDao: UserDao) {
}