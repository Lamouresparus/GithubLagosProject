package com.githublagos.ui.user_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.githublagos.domain.Result
import com.githublagos.domain.usecase.GetUserDetails
import com.githublagos.ui.mappers.toUiModel
import com.githublagos.ui.model.UserDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserDetails: GetUserDetails,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState as StateFlow<UiState>

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    fun getGithubUserDetails(username: String) {
        viewModelScope.launch(dispatcher) {
            when (val result = getUserDetails.execute(username)) {
                is Result.Success -> _uiState.emit(UiState.Loaded(result.data.toUiModel()))
                is Result.Error -> _uiState.emit(UiState.Error(result.errorMsg))
            }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        data class Error(val message: String) : UiState()
        data class Loaded(val userDetail: UserDetail) : UiState()
    }
}