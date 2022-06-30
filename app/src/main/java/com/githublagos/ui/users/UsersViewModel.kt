package com.githublagos.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.githublagos.domain.Result
import com.githublagos.domain.model.UserDomain
import com.githublagos.domain.usecase.GetGitHubUsers
import com.githublagos.ui.mappers.toUiModel
import com.githublagos.ui.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getGitHubUsers: GetGitHubUsers
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState as StateFlow<UiState>

    fun getGithubUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = getGitHubUsers.execute()) {
                is Result.Success -> _uiState.emit(UiState.Loaded(result.data))
                is Result.Error -> _uiState.emit(UiState.Error(result.errorMsg))
            }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        data class Error(val message: String) : UiState()
        data class Loaded(val users: Flow<PagingData<UserDomain>>) : UiState()
    }
}