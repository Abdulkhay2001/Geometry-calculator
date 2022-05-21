package com.abdul.geometrycalc

import android.app.Application
import com.abdul.geometrycalc.db.DataBase

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        DataBase.getInstance(this)
    }
}