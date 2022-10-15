package com.practice.hiltapplicationSample.presentations.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.hiltapplicationSample.domains.entities.Avatar
import com.practice.hiltapplicationSample.usecases.inputports.ListAvatarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AvatarListScreenViewModel @Inject constructor(
    private val listAvatarUseCase: ListAvatarUseCase
): ViewModel() {
    var avatarListState = mutableStateListOf<Avatar>()
        private set

    var isLoading = mutableStateOf(false)
        private set

    fun fetchList() {
        viewModelScope.launch {
            isLoading.value = true

            avatarListState = listAvatarUseCase.invoke().toMutableStateList()

            isLoading.value = false
        }
    }
}
