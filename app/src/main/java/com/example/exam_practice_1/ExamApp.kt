package com.example.exam_practice_1

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ExamApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ExamApp)
            modules(
                listOf(
                    database,
                    repository,
                    viewModel,
                    service
                )
            )
        }
    }
}