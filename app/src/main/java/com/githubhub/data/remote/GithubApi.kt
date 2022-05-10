package com.githubhub.data.remote

import com.githubhub.data.model.UserDetailRemote
import com.githubhub.data.model.UserRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("/users")
    suspend fun getUsers(): List<UserRemote>

    @GET("/users/{user_name}")
    suspend fun getUserDetails(@Path("user_name") userName: String): UserDetailRemote
}