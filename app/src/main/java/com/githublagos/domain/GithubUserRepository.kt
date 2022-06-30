package com.githublagos.domain

import androidx.paging.PagingData
import com.githublagos.domain.model.UserDetailDomain
import com.githublagos.domain.model.UserDomain
import kotlinx.coroutines.flow.Flow

interface GithubUserRepository {

    suspend fun getUsers(): Flow<PagingData<UserDomain>>

    suspend fun getUserDetail(username: String): UserDetailDomain
}