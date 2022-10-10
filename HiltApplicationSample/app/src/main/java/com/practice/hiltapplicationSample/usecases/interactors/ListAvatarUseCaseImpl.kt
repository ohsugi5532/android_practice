package com.practice.hiltapplicationSample.usecases.interactors

import com.practice.hiltapplicationSample.domains.entities.Avatar
import com.practice.hiltapplicationSample.domains.repositories.AvatarRepository
import com.practice.hiltapplicationSample.usecases.inputports.ListAvatarUseCase
import javax.inject.Inject

class ListAvatarUseCaseImpl @Inject constructor(
    private val repository: AvatarRepository
) : ListAvatarUseCase {
    override fun invoke(): List<Avatar> {
        return repository.findAllAvatar()
    }
}
