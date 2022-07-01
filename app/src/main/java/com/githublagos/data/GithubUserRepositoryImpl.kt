package com.githublagos.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.githublagos.data.mappers.mapToDomain
import com.githublagos.data.paging.GithubUserSource
import com.githublagos.data.remote.GithubApi
import com.githublagos.data.constants.Constants.PAGE_SIZE
import com.githublagos.domain.GithubUserRepository
import com.githublagos.domain.model.UserDetailDomain
import com.githublagos.domain.model.UserDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubUserRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi,
    private val githubUserSource: GithubUserSource
) :
    GithubUserRepository {
    override suspend fun getUsers(): Flow<PagingData<UserDomain>> {
        val config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = true,
            prefetchDistance = 5
        )
        return Pager(config) {
            githubUserSource
        }.flow
    }

    override suspend fun getUserDetail(username: String): UserDetailDomain {
        return githubApi.getUserDetails(username).mapToDomain()
    }
}