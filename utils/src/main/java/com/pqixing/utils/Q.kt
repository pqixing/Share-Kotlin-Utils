package com.pqixing.utils

import android.app.Application

/**
 * Created by pqixing on 17-8-3.
 */
object Q {
    lateinit var app: Application
    @JvmStatic
    fun isDebug() = true;
    @JvmStatic
    fun init(application: Application) {
        app = application
    }
}