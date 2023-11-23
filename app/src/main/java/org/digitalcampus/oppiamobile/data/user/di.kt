package org.digitalcampus.oppiamobile.data.user

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.digitalcampus.oppiamobile.data.config.db.AppDatabase
import org.digitalcampus.oppiamobile.data.user.db.dao.CustomFieldDao
import org.digitalcampus.oppiamobile.data.user.db.dao.UserCustomFieldDao
import org.digitalcampus.oppiamobile.data.user.db.dao.UserDao
import org.digitalcampus.oppiamobile.data.user.remote.auth.AuthRemoteService
import org.digitalcampus.oppiamobile.data.user.repository.UserDbDataSource
import org.digitalcampus.oppiamobile.data.user.repository.UserRemoteDataSource
import org.digitalcampus.oppiamobile.data.user.repository.UserRepository
import org.digitalcampus.oppiamobile.domain.useCases.UserLoginUseCase
import org.digitalcampus.oppiamobile.utils.ConnectivityUtils
import org.digitalcampus.oppiamobile.utils.FormValidator
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserModule {

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()

    @Singleton
    @Provides
    fun provideCustomFieldDao(db: AppDatabase) = db.customFieldDao()

    @Singleton
    @Provides
    fun provideUserCustomFieldDao(db: AppDatabase) = db.userCustomFieldDao()

    @Singleton
    @Provides
    fun provideAuthDbDataSource(userDao: UserDao, customFieldDao: CustomFieldDao, userCustomFieldDao: UserCustomFieldDao) = UserDbDataSource(userDao, customFieldDao, userCustomFieldDao)

    @Singleton
    @Provides
    fun provideAuthRemoteService(retrofit: Retrofit) = retrofit.create<AuthRemoteService>()

    @Singleton
    @Provides
    fun provideAuthRemoteDataSource(authRemoteService: AuthRemoteService) =
        UserRemoteDataSource(authRemoteService)

    @Singleton
    @Provides
    fun provideAuthRepository(
        authDbDataSource: UserDbDataSource,
        userRemoteDataSource: UserRemoteDataSource,
        connectivityUtils: ConnectivityUtils,
    ) = UserRepository(authDbDataSource, userRemoteDataSource, connectivityUtils)

    @Singleton
    @Provides
    fun provideUserLoginUseCase(userRepository: UserRepository) = UserLoginUseCase(userRepository)

    @Singleton
    @Provides
    fun provideFormValidator() = FormValidator()
}
