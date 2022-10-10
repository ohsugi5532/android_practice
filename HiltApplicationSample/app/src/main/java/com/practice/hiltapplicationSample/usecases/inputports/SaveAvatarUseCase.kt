package com.practice.hiltapplicationSample.usecases.inputports

import com.practice.hiltapplicationSample.domains.entities.Avatar

interface SaveAvatarUseCase {
    fun invoke(avatar: Avatar)
}
