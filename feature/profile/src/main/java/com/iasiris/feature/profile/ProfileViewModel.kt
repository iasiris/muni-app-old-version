package com.iasiris.feature.profile

import androidx.lifecycle.ViewModel
import com.iasiris.core.model.User
import com.iasiris.data.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel : ViewModel() {
    private val userRepository = UserRepository()
    private val user = userRepository.getUserByEmail("")

    private val _profileUiState = MutableStateFlow(ProfileUiState(user))
    val profileUiState: StateFlow<ProfileUiState> = _profileUiState


}

data class ProfileUiState(
    val user: User
)