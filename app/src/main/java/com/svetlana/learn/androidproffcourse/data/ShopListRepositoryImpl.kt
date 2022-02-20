package com.svetlana.learn.androidproffcourse.data

import android.app.Application
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import com.svetlana.learn.androidproffcourse.domain.ShopItem
import com.svetlana.learn.androidproffcourse.domain.ShopListRepository

class ShopListRepositoryImpl(application: Application) : ShopListRepository {

    private val shopListDao = AppDatabase.getInstance(application).shopListDao()
    private val mapper = ShopListMapper()

    override suspend fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(shopItem.id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(shopItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }
    override fun getShopList() = Transformations.map(shopListDao.getShopList()){
        mapper.mapListDbModelToListEntity(it)
    }

    /*override suspend

  fun getShopList() = MediatorLiveData<List<ShopItem>>().apply {
        addSource(shopListDao.getShopList()) {
            value = mapper.mapListDbModelToListEntity(it)
        }
    }*/
}