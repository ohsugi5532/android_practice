package com.practice.hiltapplicationSample.infrastructures.repositories

import android.content.Context
import android.util.Log
import androidx.annotation.WorkerThread
import androidx.room.Room
import com.practice.hiltapplicationSample.MainApplication
import com.practice.hiltapplicationSample.domains.entities.Avatar
import com.practice.hiltapplicationSample.domains.repositories.AvatarRepository
import com.practice.hiltapplicationSample.infrastructures.AppDatabase
import com.practice.hiltapplicationSample.infrastructures.dao.JoeSchmoe
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import javax.inject.Inject

private const val TAG = "JoeSchmoeRepositoryImpl"
private const val API_BASE = "https://joeschmoe.io/api/v1/"

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
                    svg = it.svg
                )
            }
        }
    }

    override suspend fun createAvatar(name: String): Result<Avatar> {
        val url = "$API_BASE/${name}"
        val request = Request.Builder().url(url).build()

        return kotlin.runCatching {
            val response = client.newCall(request).execute()
            val svg = response.body?.string()
            if (svg.isNullOrEmpty()) {
                throw Exception("Failed to generate Avatar")
            }

            Avatar(
                name = name,
                svg = svg
            )
        }.onFailure {
            Log.e(TAG, it.message.toString())
            return Result.failure(it)
        }
    }

    override suspend fun saveAvatar(avatar: Avatar) : Result<Unit> {
        return kotlin.runCatching {
            val db = connectDB()
            val joeSchmoe = JoeSchmoe(
                name = avatar.name,
                svg = avatar.svg,
            )
            db.joeSchmoeDao().insert(joeSchmoe)
        }
    }

    override suspend fun deleteAvatar(avatar: Avatar): Result<Unit> {
        return kotlin.runCatching {
            val db = connectDB()
            val joeSchmoe = JoeSchmoe(
                name = avatar.name,
                svg = avatar.svg,
            )
            db.joeSchmoeDao().delete(joeSchmoe)
        }
    }
}
