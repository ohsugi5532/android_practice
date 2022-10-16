package com.practice.hiltapplicationSample.usecases.inputports

import com.practice.hiltapplicationSample.domains.entities.Avatar

interface CreateAvatarUseCase {
    suspend fun invoke(name: String): Result<Avatar>
}
