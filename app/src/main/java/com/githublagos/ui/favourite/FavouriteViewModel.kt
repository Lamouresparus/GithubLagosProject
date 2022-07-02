package com.githublagos.ui.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.githublagos.domain.Result
import com.githublagos.domain.usecase.GetFavourites
import com.githublagos.ui.mappers.mapToUser
import com.githublagos.ui.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val getFavourites: GetFavourites
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState as StateFlow<UiState>

    fun getGithubUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = getFavourites.execute()) {
                is Result.Success -> _uiState.emit(UiState.Loaded(result.data.map { it.mapToUser() }))
                is Result.Error -> _uiState.emit(UiState.Error(result.errorMsg))
            }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        data class Error(val message: String) : UiState()
        data class Loaded(val users: List<User>) : UiState()
    }
}