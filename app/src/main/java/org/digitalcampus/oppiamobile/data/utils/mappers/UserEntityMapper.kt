package org.digitalcampus.oppiamobile.data.utils.mappers

import org.digitalcampus.oppiamobile.data.user.db.entity.UserEntity
import org.digitalcampus.oppiamobile.data.user.db.entity.passwordEncrypted
import org.digitalcampus.oppiamobile.domain.model.User
import org.digitalcampus.oppiamobile.utils.CryptoUtils

class UserEntityMapper : EntityMapper<UserEntity, User> {
    override fun mapFromEntity(entity: UserEntity): User {
        return User(
            email = entity.email,
            username = entity.username,
            firstName = entity.firstName,
            lastName = entity.lastName,
            password = entity.passwordEncrypted,
            isOfflineRegister = entity.offlineRegister,
        )
    }

    override fun mapToEntity(domainModel: User): UserEntity {
        return UserEntity(
            email = domainModel.email,
            username = domainModel.username,
            firstName = domainModel.firstName,
            lastName = domainModel.lastName,
            password = CryptoUtils.encryptLocalPassword(domainModel.password),
        )
    }
}
