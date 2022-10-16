package com.practice.hiltapplicationSample.presentations.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
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
    private var list = mutableStateListOf<Avatar>()
    val avatars: List<Avatar>
        get() = list

    var isLoading = mutableStateOf(false)
        private set

    fun fetchList() {
        viewModelScope.launch {
            isLoading.value = true

            val snapshotStateList = listAvatarUseCase.invoke().getOrNull() ?: SnapshotStateList<Avatar>()
            list = snapshotStateList.toMutableStateList()

            isLoading.value = false
        }
    }
}
