package com.githublagos.data.remote

import com.githublagos.data.constants.Constants.QUERY_LOCATION
import com.githublagos.data.constants.Constants.SEARCH
import com.githublagos.data.constants.Constants.USERS
import com.githublagos.data.model.UserDetailRemote
import com.githublagos.data.model.UsersResponseModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("$SEARCH/$USERS")
    suspend fun getUsers(
        @Query("q") location: String = QUERY_LOCATION,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): UsersResponseModel

    @GET("/$USERS/{user_name}")
    suspend fun getUserDetails(@Path("user_name") userName: String): UserDetailRemote
}