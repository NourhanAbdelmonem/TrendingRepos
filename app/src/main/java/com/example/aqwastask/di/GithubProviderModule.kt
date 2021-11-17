package com.example.aqwastask.di

import com.example.aqwastask.business.repositories.abstraction.GithubRepository
import com.example.aqwastask.business.repositories.impl.GithubRepositoryImpl
import com.example.aqwastask.business.usecases.abstraction.GithubUseCase
import com.example.aqwastask.business.usecases.impl.GithubUseCaseImpl
import com.example.aqwastask.framework.dataSource.GithubApi
import com.example.aqwastask.framework.dataSource.abstraction.GithubDataSource
import com.example.aqwastask.framework.dataSource.impl.GithubDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class GithubProviderModule {

    @Provides
    @Singleton
    fun provideGithubApi(retrofit: Retrofit): GithubApi =
        retrofit.create(GithubApi::class.java)

    @Provides
    @Singleton
    fun provideGithubDataSource(githubApi: GithubApi): GithubDataSource =
        GithubDataSourceImpl(githubApi)

    @Provides
    @Singleton
    fun provideGithubRepository(githubDataSource: GithubDataSource): GithubRepository =
        GithubRepositoryImpl(githubDataSource)

    @Provides
    @Singleton
    fun providesGithubUseCase(githubRepository: GithubRepository): GithubUseCase =
        GithubUseCaseImpl(githubRepository)

}