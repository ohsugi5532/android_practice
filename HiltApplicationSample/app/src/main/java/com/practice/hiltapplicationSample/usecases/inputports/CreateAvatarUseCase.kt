package com.practice.hiltapplicationSample.usecases.inputports

import com.practice.hiltapplicationSample.domains.entities.Avatar

interface CreateAvatarUseCase {
    fun invoke(name: String): Avatar
}
