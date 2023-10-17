package org.digitalcampus.oppiamobile.data.auth.repository

import org.digitalcampus.oppiamobile.data.auth.db.UserDao
import javax.inject.Inject

class AuthDbDataSource @Inject constructor(val userDao: UserDao) {
}