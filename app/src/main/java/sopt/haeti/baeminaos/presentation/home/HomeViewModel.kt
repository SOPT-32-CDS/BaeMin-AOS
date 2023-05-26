package sopt.haeti.baeminaos.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import sopt.haeti.baeminaos.data.local.StoreList
import sopt.haeti.baeminaos.module.StoreServicePool
import timber.log.Timber

class HomeViewModel : ViewModel() {

    private val _storeList =
        MutableStateFlow<List<StoreList>>(listOf())
    val storeList get() = _storeList.asStateFlow()

    init {
        fetchStoreListInfo()
    }

    private fun fetchStoreListInfo() {
        viewModelScope.launch {
            getStoreList().let {
                _storeList.value = getStoreList()
            }
        }
    }

    private suspend fun getStoreList(): List<StoreList> =
        kotlin.runCatching {
            StoreServicePool.storeListService.getStoreList()
        }.fold(
            {
                return it.toStoreList()
            }, {
                Log.e("error", it.message.toString())
                Timber.e(it.message)
                return listOf()
            }
        )
}