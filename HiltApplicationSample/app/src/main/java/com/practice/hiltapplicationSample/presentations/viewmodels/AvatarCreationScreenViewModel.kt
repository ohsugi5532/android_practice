package com.practice.hiltapplicationSample.presentations.viewmodels


import android.util.Log
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

private const val TAG = "AvatarCreationScreenViewModel"
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
            Log.d(TAG, "name or url is empty")
            return
        }

        viewModelScope.launch {
            val avatar = Avatar(name = name, url = url)
            Log.d(TAG, "save avatar with $name, $url")

            try {
                saveAvatarUseCase.invoke(avatar)
            } catch(e: Exception) {
                Log.d(TAG, "error: ${e.message}")
            }
        }
    }
}
