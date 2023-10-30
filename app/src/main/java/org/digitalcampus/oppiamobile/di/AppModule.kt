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
import org.digitalcampus.oppiamobile.utils.ConnectivityUtils
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    // TODO Al ser singleton, comprobar que al cambiar de url se cambien bien este proveedor de Retrofit
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

    // TODO Al ser singleton, comprobar que al cambiar de url se cambien bien este proveedor de ApiKey
    //TODO Hardcodeado, coger de shared prefs
    @Singleton
    @Provides
    @ApiKey
    fun provideApiKey(prefs: SharedPreferences): String = "ApiKey jbc25:896753f4968d1af4555fb70b454a27572dafe075"

    // TODO COMENTAR aquí se pueden llegar a crear muchísimos Provides, organizamos por funcionalidad o modelo?
}
