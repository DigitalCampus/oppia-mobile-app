package org.digitalcampus.oppiamobile.data.gamification

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.digitalcampus.oppiamobile.data.gamification.remote.GamificationRemoteService
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GamificationModule {


    @Singleton
    @Provides
    fun provideGamificationRemoteService(retrofit: Retrofit) = retrofit.create<GamificationRemoteService>()


}