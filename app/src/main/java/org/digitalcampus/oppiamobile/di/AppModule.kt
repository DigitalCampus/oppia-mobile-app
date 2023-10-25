package org.digitalcampus.oppiamobile.di

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.digitalcampus.oppiamobile.data.config.BASE_URL
import org.digitalcampus.oppiamobile.data.config.db.AppDatabase
import org.digitalcampus.oppiamobile.data.user.db.dao.UserDao
import org.digitalcampus.oppiamobile.data.user.remote.auth.AuthRemoteService
import org.digitalcampus.oppiamobile.data.user.repository.UserDbDataSource
import org.digitalcampus.oppiamobile.data.user.repository.UserRemoteDataSource
import org.digitalcampus.oppiamobile.data.user.repository.UserRepository
import org.digitalcampus.oppiamobile.domain.useCases.UserLoginLocalUseCase
import org.digitalcampus.oppiamobile.domain.useCases.UserLoginRemoteUseCase
import org.digitalcampus.oppiamobile.utils.ConnectivityUtils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideConnectivityUtils(app: Application) = ConnectivityUtils(app)

    @Singleton
    @Provides
    fun provideDatabase(app: Application): AppDatabase = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "oppiamobile-db",
    )
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideSharedPreferences(app: Application): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(app)

    @Singleton
    @Provides
    @BaseUrl
    fun provideBaseUrl(prefs: SharedPreferences): String = prefs.getString(BASE_URL, "https://staging.oppia-mobile.org/")
        ?: throw IllegalStateException("No default base url. Is it configured in properties file?")

    @Singleton
    @Provides
    fun provideRemote(@BaseUrl baseUrl: String): Retrofit {

        val okHttpClient = HttpLoggingInterceptor().run {
            level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder().addInterceptor(this).build()
        }


        val apiPath = "api/v2/"

        return Retrofit.Builder()
            .baseUrl("$baseUrl$apiPath")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

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
        userRemoteDataSource: UserRemoteDataSource,
    ) = UserRepository(authDbDataSource, userRemoteDataSource)

    @Singleton
    @Provides
    fun provideUserLoginRemoteUseCase(userRepository: UserRepository) = UserLoginRemoteUseCase(userRepository)

    @Singleton
    @Provides
    fun provideUserLoginLocalUseCase(userRepository: UserRepository) = UserLoginLocalUseCase(userRepository)

    // TODO COMENTAR aquí se pueden llegar a crear muchísimos Provides, organizamos por funcionalidad o modelo?
}
