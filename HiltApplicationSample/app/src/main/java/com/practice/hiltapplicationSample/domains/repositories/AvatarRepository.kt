package com.practice.hiltapplicationSample.domains.repositories

import com.practice.hiltapplicationSample.domains.entities.Avatar

interface AvatarRepository {
    suspend fun createAvatar(name: String): Result<Avatar>

    suspend fun saveAvatar(avatar: Avatar): Result<Unit>

    suspend fun findAllAvatar(): Result<List<Avatar>>

    suspend fun deleteAvatar(avatar: Avatar): Result<Unit>
}
