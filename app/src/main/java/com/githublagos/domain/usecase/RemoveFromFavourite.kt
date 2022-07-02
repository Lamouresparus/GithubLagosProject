package com.githublagos.domain.usecase

import com.githublagos.domain.GithubUserRepository
import com.githublagos.domain.Result
import com.githublagos.domain.model.UserDetailDomain
import javax.inject.Inject

class RemoveFromFavourite @Inject constructor(private val repository: GithubUserRepository) {

    suspend fun execute(user: UserDetailDomain): Result<Unit> {
        return try {
            repository.removeFromFavourite(user)
            Result.Success(Unit)
        } catch (exception: Exception) {
            Result.Error(exception.message ?: "Error Removing from Favourite")
        }

    }
}