package com.practice.hiltapplicationSample.usecases.interactors

import android.util.Log
import com.practice.hiltapplicationSample.domains.entities.Avatar
import com.practice.hiltapplicationSample.domains.repositories.AvatarRepository
import com.practice.hiltapplicationSample.usecases.inputports.ListAvatarUseCase
import javax.inject.Inject

private const val TAG = "ListAvatarUseCaseImpl"

class ListAvatarUseCaseImpl @Inject constructor(
    private val repository: AvatarRepository
) : ListAvatarUseCase {
    override suspend fun invoke(): Result<List<Avatar>> {
        return repository.findAllAvatar()
            .onSuccess {
                Log.d(TAG, "Succeeded loading avatars.")
            }
            .onFailure {
                Log.d(TAG, it.message.toString())
                Log.d(TAG, "Failed to load avatars.")
            }
    }
}
