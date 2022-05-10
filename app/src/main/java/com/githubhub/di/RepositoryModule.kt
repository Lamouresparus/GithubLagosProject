package com.githubhub.di

import com.githubhub.data.GithubUserRepositoryImpl
import com.githubhub.domain.GithubUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun dataRepository(repositoryImpl: GithubUserRepositoryImpl): GithubUserRepository
}