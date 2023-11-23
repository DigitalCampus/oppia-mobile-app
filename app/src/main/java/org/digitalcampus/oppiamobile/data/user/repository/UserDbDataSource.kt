package org.digitalcampus.oppiamobile.data.user.repository

import org.digitalcampus.oppiamobile.data.user.db.dao.CustomFieldDao
import org.digitalcampus.oppiamobile.data.user.db.dao.UserCustomFieldDao
import org.digitalcampus.oppiamobile.data.user.db.dao.UserDao
import org.digitalcampus.oppiamobile.data.user.db.entity.UserCustomFieldEntity
import org.digitalcampus.oppiamobile.data.user.db.entity.UserEntity
import org.digitalcampus.oppiamobile.data.utils.mappers.UserEntityMapper
import org.digitalcampus.oppiamobile.domain.model.BooleanValue
import org.digitalcampus.oppiamobile.domain.model.FloatValue
import org.digitalcampus.oppiamobile.domain.model.IntValue
import org.digitalcampus.oppiamobile.domain.model.StringValue
import org.digitalcampus.oppiamobile.domain.model.User
import javax.inject.Inject

class UserDbDataSource @Inject constructor(
    private val userDao: UserDao,
    private val customFieldDao: CustomFieldDao,
    private val userCustomFieldDao: UserCustomFieldDao,
) {

    suspend fun insertUser(user: User) {
        val userEntity: UserEntity = UserEntityMapper().mapToEntity(user)
        val userId = userDao.insert(userEntity)
        insertUserCustomFields(userId, user)
    }

    suspend fun updateUser(user: UserEntity) = userDao.update(user)

    suspend fun getByUsername(username: String) = userDao.getByUsername(username)

    private suspend fun insertUserCustomFields(userId: Long, user: User) {
        for ((key, customField) in user.userCustomFields) {
            var userCustomFieldEntity = UserCustomFieldEntity(
                userId = userId,
                fieldKey = key,
                valueStr = (customField.value as? StringValue)?.value,
                valueBool = (customField.value as? BooleanValue)?.value,
                valueInt = (customField.value as? IntValue)?.value,
                valueFloat = (customField.value as? FloatValue)?.value,
            )
            userCustomFieldDao.insert(userCustomFieldEntity)
        }
    }
}
