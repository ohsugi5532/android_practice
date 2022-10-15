package com.practice.hiltapplicationSample.infrastructures

import androidx.room.Database
import androidx.room.RoomDatabase
import com.practice.hiltapplicationSample.infrastructures.dao.JoeSchmoe
import com.practice.hiltapplicationSample.infrastructures.dao.JoeSchmoeDao

// Not: https://pg.akihiro-takeda.com/android-room-error/#toc1
@Database(entities = [JoeSchmoe::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun joeSchmoeDao(): JoeSchmoeDao
}
