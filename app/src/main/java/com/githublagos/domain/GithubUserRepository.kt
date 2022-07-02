package com.githublagos.domain

import androidx.paging.PagingData
import com.githublagos.domain.model.UserDetailDomain
import com.githublagos.domain.model.UserDomain
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface GithubUserRepository {

    suspend fun getUsers(): Flow<PagingData<UserDomain>>

    suspend fun getUserDetail(username: String): UserDetailDomain

    suspend fun getFavourites(): List<UserDetailDomain>

    suspend fun saveToFavourite(user: UserDetailDomain)

    suspend fun getFromFavourite(username: String): UserDetailDomain

    suspend fun removeFromFavourite(user: UserDetailDomain)
}