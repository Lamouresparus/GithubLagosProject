package com.githublagos.ui.users

import androidx.paging.PagingData
import app.cash.turbine.test
import com.githublagos.domain.Result
import com.githublagos.domain.model.UserDomain
import com.githublagos.domain.usecase.GetGitHubUsers
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class UsersViewModelTest {

    private val getGitHubUsers = mockk<GetGitHubUsers>()

    private val sut = UsersViewModel(getGitHubUsers)

    @Test
    fun `Users Fetched successfully - correct state emitted `() = runTest {

        val users = flow {
            emit(
                PagingData.from(
                    listOf(
                        UserDomain(
                            "love",
                            "image",
                            "github.com/lamoure"
                        )
                    )
                )
            )
        }

        coEvery { getGitHubUsers.execute() } returns Result.Success(users)

        sut.uiState.test {
            sut.getGithubUsers()

            Assert.assertEquals(UsersViewModel.UiState.Loading, awaitItem())


            val content = awaitItem()

            Assert.assertEquals(UsersViewModel.UiState.Loaded(users), content)

            cancelAndConsumeRemainingEvents()
        }

    }

    @Test
    fun `UsersFetched Failed - Correct state emitted`() = runTest {

        val errorMessage = "error fetching github users"
        val result = Result.Error(errorMessage)

        coEvery { getGitHubUsers.execute() } returns result

        sut.uiState.test {
            sut.getGithubUsers()

            Assert.assertEquals(UsersViewModel.UiState.Loading, awaitItem())

            Assert.assertEquals(UsersViewModel.UiState.Error(errorMessage), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }
}