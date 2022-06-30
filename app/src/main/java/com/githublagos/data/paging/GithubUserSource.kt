package com.githublagos.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.githublagos.data.mappers.mapToDomain
import com.githublagos.data.model.UserRemote
import com.githublagos.data.remote.GithubApi
import com.githublagos.domain.model.UserDomain
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GithubUserSource @Inject constructor(private val api: GithubApi) :
    PagingSource<Int, UserDomain>() {

    override fun getRefreshKey(state: PagingState<Int, UserDomain>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserDomain> {
        return try {
            val nextPage = params.key ?: 1
            val userList = api.getUsers(page = nextPage).map { it.mapToDomain() }
            LoadResult.Page(
                data = userList,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (userList.isEmpty()) null else nextPage + 3
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}