package com.githublagos.ui.user_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.githublagos.domain.Result
import com.githublagos.domain.usecase.AddToFavourite
import com.githublagos.domain.usecase.GetFavouriteUser
import com.githublagos.domain.usecase.GetUserDetails
import com.githublagos.domain.usecase.RemoveFromFavourite
import com.githublagos.ui.mappers.mapToDomain
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
    private val addToFavourite: AddToFavourite,
    private val getFavouriteUser: GetFavouriteUser,
    private val removeFromFavourite: RemoveFromFavourite
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState as StateFlow<UiState>

    private val _isFavourite = MutableStateFlow<Boolean>(false)
    val isFavourite = _isFavourite as StateFlow<Boolean>

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    fun getGithubUserDetails(username: String) {
        viewModelScope.launch(dispatcher) {
            when (val result = getFavouriteUser.execute(username)) {
                is Result.Success -> {
                    _uiState.emit(UiState.Loaded(result.data.toUiModel()))
                    _isFavourite.emit(true)
                }
                is Result.Error -> {
                    when (val response = getUserDetails.execute(username)) {
                        is Result.Success -> _uiState.emit(UiState.Loaded(response.data.toUiModel()))
                        is Result.Error -> _uiState.emit(UiState.Error(result.errorMsg))
                    }
                }
            }
        }
    }

    fun addOrRemoveFromFavourite(user: UserDetail) {

        viewModelScope.launch(dispatcher) {
            if (isFavourite.value) {
                when (removeFromFavourite.execute(user.mapToDomain())) {
                    is Result.Success -> {
                        _isFavourite.emit(false)
                    }
                    is Result.Error -> {
                        _isFavourite.emit(true)
                    }
                }
            } else {
                when (addToFavourite.execute(user.mapToDomain())) {
                    is Result.Success -> {
                        _isFavourite.emit(true)
                    }
                    is Result.Error -> {
                        _isFavourite.emit(false)
                    }
                }
            }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        data class Error(val message: String) : UiState()
        data class Loaded(val userDetail: UserDetail) : UiState()
    }
}