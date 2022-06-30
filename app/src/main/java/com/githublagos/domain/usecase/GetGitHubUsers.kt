package com.githublagos.domain.usecase

import androidx.paging.PagingData
import com.githublagos.domain.GithubUserRepository
import com.githublagos.domain.Result
import com.githublagos.domain.model.UserDomain
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGitHubUsers @Inject constructor(private val repository: GithubUserRepository) {

    suspend fun execute(): Result<Flow<PagingData<UserDomain>>> {
        return try {
            val response = repository.getUsers()
            Result.Success(response)
        } catch (exception: Exception) {
            Result.Error(exception.message ?: "Error Fetching users")
        }

    }
}