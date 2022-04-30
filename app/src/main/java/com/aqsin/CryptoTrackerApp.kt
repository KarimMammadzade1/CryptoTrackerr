package com.aqsin

import android.app.Application
import androidx.work.*
import com.aqsin.cryptotracker.work.CryptoTrackerWork
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class CryptoTrackerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        val workManager: WorkManager = WorkManager.getInstance(this)
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val periodicWork = PeriodicWorkRequest.Builder(CryptoTrackerWork::class.java, 15, TimeUnit.MINUTES)
            .setBackoffCriteria(BackoffPolicy.LINEAR, PeriodicWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
            .addTag("WorkTag")
            .setConstraints(constraints)
            .build()
        workManager.enqueueUniquePeriodicWork(
            "Workum",
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWork

        )
    }
}