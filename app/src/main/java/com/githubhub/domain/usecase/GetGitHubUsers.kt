package com.githubhub.domain.usecase

import com.githubhub.domain.GithubUserRepository
import com.githubhub.domain.Result
import com.githubhub.domain.model.UserDomain
import javax.inject.Inject

class GetGitHubUsers @Inject constructor(private val repository: GithubUserRepository) {

    suspend fun execute(): Result<List<UserDomain>> {
        return try {
            val response = repository.getUsers()
            Result.Success(response)
        } catch (exception: Exception){
            Result.Error(exception.message ?: "Error Fetching users")
        }

    }
}