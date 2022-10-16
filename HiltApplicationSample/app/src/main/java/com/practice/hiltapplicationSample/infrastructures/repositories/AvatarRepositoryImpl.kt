package com.practice.hiltapplicationSample.infrastructures.repositories

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.practice.hiltapplicationSample.domains.entities.Avatar
import com.practice.hiltapplicationSample.domains.repositories.AvatarRepository
import com.practice.hiltapplicationSample.infrastructures.AppDatabase
import com.practice.hiltapplicationSample.infrastructures.dao.JoeSchmoe
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import javax.inject.Inject

private const val TAG = "JoeSchmoeRepositoryImpl"

class AvatarRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
): AvatarRepository {
    private val client: OkHttpClient = OkHttpClient()

    private fun connectDB(): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "avatar_database"
        ).build()
    }


    override suspend fun findAllAvatar(): Result<List<Avatar>> {
        return kotlin.runCatching {
            val db = connectDB()
            db.joeSchmoeDao().findAll().map {
                Avatar(
                    name = it.name,
                    url = it.url
                )
            }
        }
    }

    override suspend fun saveAvatar(avatar: Avatar) : Result<Unit> {
        return kotlin.runCatching {
            val db = connectDB()
            val joeSchmoe = JoeSchmoe(
                name = avatar.name,
                url = avatar.url,
            )
            db.joeSchmoeDao().insert(joeSchmoe)
        }
    }
}
