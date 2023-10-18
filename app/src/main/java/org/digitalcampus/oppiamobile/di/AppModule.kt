package org.digitalcampus.oppiamobile.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.digitalcampus.oppiamobile.data.auth.db.UserDao
import org.digitalcampus.oppiamobile.data.auth.remote.AuthRemoteService
import org.digitalcampus.oppiamobile.data.auth.repository.AuthDbDataSource
import org.digitalcampus.oppiamobile.data.auth.repository.AuthRemoteDataSource
import org.digitalcampus.oppiamobile.data.auth.repository.AuthRepository
import org.digitalcampus.oppiamobile.data.config.db.AppDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application): AppDatabase = Room.databaseBuilder(
        app,
        AppDatabase::class.java, "oppiamobile-db"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideRemote(): Retrofit {

        val okHttpClient = HttpLoggingInterceptor().run {
            level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder().addInterceptor(this).build()
        }

        return Retrofit.Builder()
            .baseUrl("https://staging.oppia-mobile.org/api/v2/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()

    @Singleton
    @Provides
    fun provideAuthDbDataSource(userDao: UserDao) = AuthDbDataSource(userDao)

    @Singleton
    @Provides
    fun provideAuthRemoteService(retrofit: Retrofit) = retrofit.create<AuthRemoteService>()

    @Singleton
    @Provides
    fun provideAuthRemoteDataSource(authRemoteService: AuthRemoteService) = AuthRemoteDataSource(authRemoteService)

    @Singleton
    @Provides
    fun provideAuthRepository(
        authDbDataSource: AuthDbDataSource,
        authRemoteDataSource: AuthRemoteDataSource
    ) = AuthRepository(authDbDataSource, authRemoteDataSource)

}