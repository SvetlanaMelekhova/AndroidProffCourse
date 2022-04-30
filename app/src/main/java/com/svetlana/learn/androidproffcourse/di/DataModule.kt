package com.svetlana.learn.androidproffcourse.di

import android.app.Application
import com.svetlana.learn.androidproffcourse.data.AppDatabase
import com.svetlana.learn.androidproffcourse.data.ShopListDao
import com.svetlana.learn.androidproffcourse.data.ShopListRepositoryImpl
import com.svetlana.learn.androidproffcourse.di.annotation.ApplicationScope
import com.svetlana.learn.androidproffcourse.domain.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindShopListRepository(impl: ShopListRepositoryImpl): ShopListRepository

    companion object{

        @ApplicationScope
        @Provides
        fun provideShopListDao(
            application: Application
        ): ShopListDao {
            return AppDatabase.getInstance(application).shopListDao()
        }
    }
}