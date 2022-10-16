package com.practice.hiltapplicationSample.usecases.inputports

import com.practice.hiltapplicationSample.domains.entities.Avatar

interface SaveAvatarUseCase {
    suspend fun invoke(avatar: Avatar): Result<Unit>
}
