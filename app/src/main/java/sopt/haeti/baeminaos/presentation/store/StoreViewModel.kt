package sopt.haeti.baeminaos.presentation.store

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import sopt.haeti.baeminaos.data.remote.StoreDetailResponseDto
import sopt.haeti.baeminaos.module.StoreServicePool
import timber.log.Timber

class StoreViewModel : ViewModel() {

    private val _menuCategory =
        MutableStateFlow<List<StoreDetailResponseDto.Data.MenuCategory>>(listOf())
    val menuCategory get() = _menuCategory.asStateFlow()

    init {
        fetchStoreMenuInfo()
    }

    private fun fetchStoreMenuInfo() {
        viewModelScope.launch {
            getStoreMenuInfo().let {
                _menuCategory.value = getStoreMenuInfo()
            }
        }
    }

    private suspend fun getStoreMenuInfo(): List<StoreDetailResponseDto.Data.MenuCategory> =
        kotlin.runCatching {
            StoreServicePool.storeService.getStoreDetail()
        }.fold(
            {
                return it.datas.menu_categories
            }, {
                Log.e("error", it.message.toString())
                Timber.e(it.message)
                return listOf()
            }
        )
}