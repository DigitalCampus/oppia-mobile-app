package org.digitalcampus.oppiamobile.data.course

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.digitalcampus.oppiamobile.data.config.db.AppDatabase
import org.digitalcampus.oppiamobile.data.course.remote.CourseRemoteService
import org.digitalcampus.oppiamobile.data.course.repository.CourseRemoteDataSource
import org.digitalcampus.oppiamobile.di.ApiKey
import org.digitalcampus.oppiamobile.domain.useCases.TestApiClientUseCase
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CourseModule {

    @Singleton
    @Provides
    fun provideCourseDao(db: AppDatabase) = db.courseDao()

//    @Singleton
//    @Provides
//    fun provideAuthDbDataSource(userDao: UserDao) = UserDbDataSource(userDao)

    @Singleton
    @Provides
    fun provideCourseRemoteService(retrofit: Retrofit) = retrofit.create<CourseRemoteService>()

    @Singleton
    @Provides
    fun provideCourseRemoteDataSource(courseRemoteService: CourseRemoteService) =
        CourseRemoteDataSource(courseRemoteService)

//    @Singleton
//    @Provides
//    fun provideAuthRepository(
//        authDbDataSource: UserDbDataSource,
//        userRemoteDataSource: UserRemoteDataSource
//    ) = UserRepository(authDbDataSource, userRemoteDataSource)

    // TODO for testing api client. Remove at end
    @Singleton
    @Provides
    fun provideTestApiUseCase(courseRemoteService: CourseRemoteService, @ApiKey apiKey: String) =
        TestApiClientUseCase(courseRemoteService, apiKey)
}
