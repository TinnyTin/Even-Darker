package com.example.evendarker

import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.IBinder
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout

class ScreenFilterService: Service() {
    val STATE_ACTIVE: Int = 1
    val STATE_INACTIVE: Int = 0
    var STATE: Int = STATE_INACTIVE

    private lateinit var screenFilter: View

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()

        screenFilter = LinearLayout(this)
        screenFilter.setBackgroundColor(Color.BLACK)

        val layoutParams: WindowManager.LayoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            280,
            PixelFormat.TRANSLUCENT
        )

        val windowManager:WindowManager = (getSystemService(WINDOW_SERVICE) as WindowManager?)!!
        windowManager.addView(screenFilter,layoutParams)

        STATE = STATE_ACTIVE
    }

    override fun onDestroy() {
        super.onDestroy()
        val windowManager:WindowManager = (getSystemService(WINDOW_SERVICE) as WindowManager?)!!
        windowManager.removeView(screenFilter)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        screenFilter.setBackgroundColor(Color.BLACK)
        return super.onStartCommand(intent, flags, startId)
    }
}