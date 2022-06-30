package com.githublagos.domain.usecase

import com.githublagos.domain.GithubUserRepository
import com.githublagos.domain.Result
import com.githublagos.domain.model.UserDetailDomain
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