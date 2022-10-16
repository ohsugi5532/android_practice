package com.practice.hiltapplicationSample.presentations.viewmodels


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.hiltapplicationSample.domains.entities.Avatar
import com.practice.hiltapplicationSample.usecases.inputports.SaveAvatarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AvatarCreationScreenViewModel @Inject constructor(
    private val saveAvatarUseCase: SaveAvatarUseCase,
): ViewModel() {
    private val baseUri = "https://joeschmoe.io/api/v1"

    fun generateAvatarUrl(name: String): String {
        return if (name.isNotEmpty()) {
            "$baseUri/$name"
        } else {
            ""
        }
    }

    fun saveAvatar(name: String, url: String) {
        if (name.isEmpty() || url.isEmpty()) {
            return
        }

        viewModelScope.launch {
            val avatar = Avatar(name = name, url = url)
            saveAvatarUseCase.invoke(avatar)
        }
    }
}
