package com.githubhub.data

import com.githubhub.data.mappers.mapToDomain
import com.githubhub.data.remote.GithubApi
import com.githubhub.domain.GithubUserRepository
import com.githubhub.domain.model.UserDetailDomain
import com.githubhub.domain.model.UserDomain
import javax.inject.Inject

class GithubUserRepositoryImpl @Inject constructor(private val githubApi: GithubApi) : GithubUserRepository {
    override suspend fun getUsers(): List<UserDomain> {
        return githubApi.getUsers().map {
            it.mapToDomain()
        }
    }

    override suspend fun getUserDetail(username: String): UserDetailDomain {
        return githubApi.getUserDetails(username).mapToDomain()
    }
}