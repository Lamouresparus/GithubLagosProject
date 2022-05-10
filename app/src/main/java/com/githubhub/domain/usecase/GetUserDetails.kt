package com.githubhub.domain.usecase

import com.githubhub.domain.GithubUserRepository
import com.githubhub.domain.Result
import com.githubhub.domain.model.UserDetailDomain
import javax.inject.Inject

class GetUserDetails @Inject constructor(private val repository: GithubUserRepository) {
    suspend fun execute(username: String): Result<UserDetailDomain> {
        return try {
            val response = repository.getUserDetail(username)
            Result.Success(response)
        } catch (exception: Exception) {
            Result.Error(exception.message ?: "Error fetching user details")
        }
    }
}