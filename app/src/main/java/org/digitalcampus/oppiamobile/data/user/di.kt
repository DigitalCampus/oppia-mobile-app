package org.digitalcampus.oppiamobile.data.user

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.digitalcampus.oppiamobile.data.config.db.AppDatabase
import org.digitalcampus.oppiamobile.data.user.db.dao.UserDao
import org.digitalcampus.oppiamobile.data.user.remote.auth.AuthRemoteService
import org.digitalcampus.oppiamobile.data.user.repository.UserDbDataSource
import org.digitalcampus.oppiamobile.data.user.repository.UserRemoteDataSource
import org.digitalcampus.oppiamobile.data.user.repository.UserRepository
import org.digitalcampus.oppiamobile.domain.use_cases.UserLoginLocalUseCase
import org.digitalcampus.oppiamobile.domain.use_cases.UserLoginRemoteUseCase
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
    fun provideAuthDbDataSource(userDao: UserDao) = UserDbDataSource(userDao)

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
        userRemoteDataSource: UserRemoteDataSource
    ) = UserRepository(authDbDataSource, userRemoteDataSource)

    @Singleton
    @Provides
    fun provideUserLoginRemoteUseCase(userRepository: UserRepository) = UserLoginRemoteUseCase(userRepository)

    @Singleton
    @Provides
    fun provideUserLoginLocalUseCase(userRepository: UserRepository) = UserLoginLocalUseCase(userRepository)
}