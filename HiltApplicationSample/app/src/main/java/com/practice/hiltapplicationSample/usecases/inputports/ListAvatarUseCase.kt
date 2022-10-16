package com.practice.hiltapplicationSample.usecases.inputports

import com.practice.hiltapplicationSample.domains.entities.Avatar

interface ListAvatarUseCase {
    suspend fun invoke(): Result<List<Avatar>>
}
