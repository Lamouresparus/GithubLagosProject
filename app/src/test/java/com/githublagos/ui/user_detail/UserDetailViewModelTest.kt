package com.githublagos.ui.user_detail

import app.cash.turbine.test
import com.githublagos.domain.Result
import com.githublagos.domain.model.UserDetailDomain
import com.githublagos.domain.usecase.AddToFavourite
import com.githublagos.domain.usecase.GetFavouriteUser
import com.githublagos.domain.usecase.GetUserDetails
import com.githublagos.domain.usecase.RemoveFromFavourite
import com.githublagos.ui.mappers.toUiModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserDetailViewModelTest {

    private val getUserDetail = mockk<GetUserDetails>()
    private val addToFavourite = mockk<AddToFavourite>()
    private val getFavouriteUser = mockk<GetFavouriteUser>()
    private val removeFromFavourite = mockk<RemoveFromFavourite>()
    private val sut = UserDetailViewModel(getUserDetail, addToFavourite, getFavouriteUser, removeFromFavourite)

    private val username = "love"

    @Test
    fun `User Detail Fetched successfully - correct state emitted`() = runTest {

        val bio = "Hello, I am Love, I am an Android Developer and Technical Writer"
        val favErrorResult = Result.Error("No user favourite user found with username $username")
        val userDetail = UserDetailDomain(
            username,
            "image",
            "github.com/lamoure",
            "Love Otudor",
            "Uyo, Nigeria",
            11,
            4,
            51,
            bio
        )
        coEvery { getUserDetail.execute(username) } returns Result.Success(userDetail)
        coEvery { getFavouriteUser.execute(username) } returns favErrorResult

        sut.uiState.test {
            sut.getGithubUserDetails(username)

            Assert.assertEquals(UserDetailViewModel.UiState.Loading, awaitItem())

            val expected = userDetail.toUiModel()
            val content = awaitItem()

            Assert.assertEquals(UserDetailViewModel.UiState.Loaded(expected), content)
            Assert.assertEquals(bio, (content as UserDetailViewModel.UiState.Loaded).userDetail.bio)
            cancelAndConsumeRemainingEvents()
        }

    }

    @Test
    fun `UsersFetched Failed - Correct state emitted`() = runTest {
        val errorMessage = "error fetching github user detail"
        val result = Result.Error(errorMessage)

        coEvery { getFavouriteUser.execute(username) } returns result
        coEvery { getUserDetail.execute(username) } returns result

        sut.uiState.test {
            sut.getGithubUserDetails(username)

            Assert.assertEquals(UserDetailViewModel.UiState.Loading, awaitItem())

            Assert.assertEquals(UserDetailViewModel.UiState.Error(errorMessage), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }
}