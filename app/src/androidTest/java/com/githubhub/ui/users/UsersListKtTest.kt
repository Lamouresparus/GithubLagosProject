package com.githubhub.ui.users

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.githubhub.MainActivity
import com.githubhub.R
import com.githubhub.ui.model.User
import org.junit.Rule
import org.junit.Test

class UsersListKtTest {

    @get:Rule
    val composeRule = createAndroidComposeRule(MainActivity::class.java)

    private val user = User("lamouresparus", "", "https://github.com/Lamouresparus")

    @Test
    fun testLoadingComposableIsRenderedWhenViewStateIsLoading() {

        val uiState = UsersViewModel.UiState.Loading

        composeRule.apply {
            setContent { UserListScreen(uiState = uiState) {} }
            onNodeWithTag(composeRule.activity.getString(R.string.test_tag_circular_progress)).assertIsDisplayed()
        }
    }

    @Test
    fun testErrorTextIsShownWhenViewStateIsError() {

        val uiState = UsersViewModel.UiState.Error("Error loading Users")

        composeRule.apply {
            setContent { UserListScreen(uiState = uiState) {} }
            onNodeWithTag(composeRule.activity.getString(R.string.error_test_tag)).assertIsDisplayed()
        }
    }

    @Test
    fun testUserListIsShownWhenViewStateIsLoaded() {

        val uiState = UsersViewModel.UiState.Loaded(listOf(user, user, user, user, user, user))

        composeRule.apply {
            setContent { UserListScreen(uiState = uiState) {} }
            onAllNodesWithTag(composeRule.activity.getString(R.string.test_tag_user_card)).assertCountEquals(6)
        }
    }

    @Test
    fun testClickOnGithubIconOpensWebBrowser() {

        val uiState = UsersViewModel.UiState.Loaded(listOf(user, user, user, user, user, user))

        composeRule.apply {
            setContent { UserListScreen(uiState = uiState) {} }
            onAllNodesWithTag(composeRule.activity.getString(R.string.github_icon_test_tag)).onFirst().performClick()
            onNodeWithTag(composeRule.activity.getString(R.string.github_icon_test_tag)).assertDoesNotExist()

        }
    }
}