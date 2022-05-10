package com.githubhub.ui.users

import app.cash.turbine.test
import com.githubhub.domain.Result
import com.githubhub.domain.model.UserDomain
import com.githubhub.domain.usecase.GetGitHubUsers
import com.githubhub.ui.mappers.toUiModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class UsersViewModelTest {

    private val getGitHubUsers = mockk<GetGitHubUsers>()

    private val sut = UsersViewModel(getGitHubUsers)

    @Test
    fun `Users Fetched successfully - correct state emitted `() = runTest {

        val users = listOf(UserDomain("love", "image", "github.com/lamoure"))
        coEvery { getGitHubUsers.execute() } returns Result.Success(users)

        sut.uiState.test {
            sut.getGithubUsers()

            Assert.assertEquals(UsersViewModel.UiState.Loading, awaitItem())


            val expected = users.map { it.toUiModel() }
            val content = awaitItem()

            Assert.assertEquals(UsersViewModel.UiState.Loaded(expected), content)
            Assert.assertEquals("love",(content as UsersViewModel.UiState.Loaded).users.first().username)
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