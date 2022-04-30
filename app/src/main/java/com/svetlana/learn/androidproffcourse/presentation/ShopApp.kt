package com.svetlana.learn.androidproffcourse.presentation

import android.app.Application
import com.svetlana.learn.androidproffcourse.di.DaggerAppComponent

class ShopApp: Application() {

    val component by lazy {
        DaggerAppComponent.factory()
            .create(this)
    }
}