package com.practice.hiltapplicationSample.presentations.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.hiltapplicationSample.domains.entities.Avatar
import com.practice.hiltapplicationSample.usecases.inputports.CreateAvatarUseCase
import com.practice.hiltapplicationSample.usecases.inputports.SaveAvatarUseCase
import com.practice.hiltapplicationSample.usecases.interactors.CreateAvatarUseCaseImpl
import com.practice.hiltapplicationSample.usecases.interactors.SaveAvatarUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AvatarCreationScreenViewModel @Inject constructor(
    private val createAvatarUseCase: CreateAvatarUseCase,
    private val saveAvatarUseCase: SaveAvatarUseCase,
): ViewModel() {
    var avatarState by mutableStateOf<Avatar?>(null)

    fun createAvatar(name: String) {
        if (name.isNotEmpty()) {
            viewModelScope.launch {
                withContext(Dispatchers.Default) {
                    avatarState = createAvatarUseCase.invoke(name)
                }
            }
        }
    }

    fun saveAvatar() {
        avatarState?.let { avatar ->
            if (avatar.name.isEmpty() || avatar.svg.isEmpty()) {
                return
            }

            viewModelScope.launch {
                saveAvatarUseCase.invoke(avatar)

                avatarState = null
            }
        }
    }
}
