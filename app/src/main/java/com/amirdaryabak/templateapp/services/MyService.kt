package com.amirdaryabak.templateapp.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    val TAG = "MyService"

    init {
        Log.d(TAG, "Service in running...")
    }

    override fun onBind(p0: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val data = intent?.getSerializableExtra("EXTRA_DATA")
        data?.let {
            Log.d(TAG, data.toString())
        }
        // START_REDELIVER_INTENT
        // START_STICKY
        // START_NOT_STICKY
        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Service in Destroyed")
    }

}