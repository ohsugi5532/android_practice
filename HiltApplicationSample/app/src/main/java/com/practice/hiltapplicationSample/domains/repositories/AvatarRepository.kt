package com.practice.hiltapplicationSample.domains.repositories

import com.practice.hiltapplicationSample.domains.entities.Avatar

interface AvatarRepository {
    fun createAvatar(name: String): Result<Avatar>

    fun saveAvatar(avatar: Avatar)

    fun findAllAvatar(): List<Avatar>

    fun deleteAvatar(avatar: Avatar)
}
