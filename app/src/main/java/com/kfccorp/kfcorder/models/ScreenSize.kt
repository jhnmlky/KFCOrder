package com.kfccorp.kfcorder.models

import android.content.Context
import androidx.window.layout.WindowMetricsCalculator

class ScreenSize {
    companion object {
        var SCREEN_WIDTH : Int = 0
        var SCREEN_HEIGHT : Int = 0

        fun getScreenSize(context: Context) {
            val wm = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(context)
            val bounds = wm.bounds
            SCREEN_WIDTH = bounds.width()
            SCREEN_HEIGHT = bounds.height()
        }
    }
}