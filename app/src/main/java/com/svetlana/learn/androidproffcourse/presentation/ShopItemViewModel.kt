package com.svetlana.learn.androidproffcourse.presentation

import android.app.Application
import androidx.lifecycle.*
import com.svetlana.learn.androidproffcourse.data.ShopListRepositoryImpl
import com.svetlana.learn.androidproffcourse.domain.AddShopItemUseCase
import com.svetlana.learn.androidproffcourse.domain.EditShopItemUseCase
import com.svetlana.learn.androidproffcourse.domain.GetShopItemUseCase
import com.svetlana.learn.androidproffcourse.domain.ShopItem
import kotlinx.coroutines.*
import java.lang.Exception

class ShopItemViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ShopListRepositoryImpl(application)

    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem


    fun getShopItem(shopItemId: Int) {
        viewModelScope.launch {
            val item = getShopItemUseCase.getShopItem(shopItemId)
            _shopItem.value = item
        }
    }

    fun addShopItem(inputName: String?, inputCount: String?) {

        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {

            viewModelScope.launch {
                val shopItem = ShopItem(name, count, true)
                addShopItemUseCase.addShopItem(shopItem)
                finishWork()
            }
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?) {

        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            _shopItem.value?.let {
                viewModelScope.launch {
                    val shopItem = it.copy(name = name, count = count)
                    editShopItemUseCase.editShopItem(shopItem)
                    finishWork()
                }
            }
        }
    }

    private fun parseName(inputName: String?) = inputName?.trim() ?: ""

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true

        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }

        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun finishWork(){
        _shouldCloseScreen.value = Unit
    }
}