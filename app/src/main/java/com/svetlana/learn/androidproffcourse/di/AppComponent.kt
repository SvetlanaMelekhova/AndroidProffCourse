package com.svetlana.learn.androidproffcourse.di

import android.app.Application
import com.svetlana.learn.androidproffcourse.di.annotation.ApplicationScope
import com.svetlana.learn.androidproffcourse.presentation.MainActivity
import com.svetlana.learn.androidproffcourse.presentation.ShopItemFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: ShopItemFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}