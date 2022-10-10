package com.practice.hiltapplicationSample

import android.app.Application
import androidx.room.Room
import com.practice.hiltapplicationSample.infrastructures.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication: Application() {
    companion object {
        fun getApplication(): MainApplication {
            return this.getApplication()
        }
    }
}
