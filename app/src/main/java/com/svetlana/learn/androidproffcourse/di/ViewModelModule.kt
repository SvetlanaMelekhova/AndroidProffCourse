package com.svetlana.learn.androidproffcourse.di

import androidx.lifecycle.ViewModel
import com.svetlana.learn.androidproffcourse.di.annotation.ViewModelKey
import com.svetlana.learn.androidproffcourse.presentation.MainViewModel
import com.svetlana.learn.androidproffcourse.presentation.ShopItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindsMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShopItemViewModel::class)
    fun bindsShopItemViewModel(viewModel: ShopItemViewModel): ViewModel
}