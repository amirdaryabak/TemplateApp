package com.amirdaryabak.templateapp

import android.app.Application
import com.amirdaryabak.templateapp.eventbus.MyEvent
import dagger.hilt.android.HiltAndroidApp
import org.greenrobot.eventbus.EventBus
import timber.log.Timber
import timber.log.Timber.DebugTree
import java.util.*


@HiltAndroidApp
class MyApplication : Application(){
    private val timer = Timer()

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
        timer.schedule(object : TimerTask() {
            override fun run() {
                EventBus.getDefault().post(MyEvent())
            }
        }, 3000, 3000)
    }
}