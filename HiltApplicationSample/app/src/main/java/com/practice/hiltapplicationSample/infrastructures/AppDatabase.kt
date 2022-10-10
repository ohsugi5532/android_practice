package com.practice.hiltapplicationSample.infrastructures

import androidx.room.Database
import androidx.room.RoomDatabase
import com.practice.hiltapplicationSample.infrastructures.dao.JoeSchmoe
import com.practice.hiltapplicationSample.infrastructures.dao.JoeSchmoeDao

@Database(entities = [JoeSchmoe::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun joeSchmoeDao(): JoeSchmoeDao
}
