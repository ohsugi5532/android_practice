package com.practice.hiltapplicationSample.usecases.interactors

import android.util.Log
import com.practice.hiltapplicationSample.domains.entities.Avatar
import com.practice.hiltapplicationSample.domains.repositories.AvatarRepository
import com.practice.hiltapplicationSample.usecases.inputports.SaveAvatarUseCase
import javax.inject.Inject

private const val TAG = "SaveAvatarUseCaseImpl"

class SaveAvatarUseCaseImpl @Inject constructor(
    private val repository: AvatarRepository
): SaveAvatarUseCase {
    override suspend fun invoke(avatar: Avatar): Result<Unit> {
       return repository.saveAvatar(avatar)
           .onSuccess {
               Log.d(TAG, "Succeeded saving an avatar.")
           }
           .onFailure {
               Log.d(TAG, it.message.toString())
               Log.d(TAG, "Failed to save an avatar.")
           }
    }
}
