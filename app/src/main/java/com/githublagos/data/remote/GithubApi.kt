package com.githublagos.data.remote

import com.githublagos.data.model.UserDetailRemote
import com.githublagos.data.model.UserRemote
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("/users")
    suspend fun getUsers(
        @Query("q") location: String = "lagos",
        @Query("page") page: Int
    ): List<UserRemote>

    @GET("/users/{user_name}")
    suspend fun getUserDetails(@Path("user_name") userName: String): UserDetailRemote
}