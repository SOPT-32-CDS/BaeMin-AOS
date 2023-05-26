package sopt.haeti.baeminaos.presentation.storedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sopt.haeti.baeminaos.data.remote.StoreDetailData
import sopt.haeti.baeminaos.module.StoreDetailServicePool

class StoreDetailViewModel : ViewModel() {

    private val _responseResult: MutableLiveData<StoreDetailData> = MutableLiveData()
    val responseResult: LiveData<StoreDetailData> = _responseResult

    private val _errorResult: MutableLiveData<String> = MutableLiveData()
    val errorResult: LiveData<String> = _errorResult

    fun getMenuFromServer() {
        StoreDetailServicePool.storeDetailService.getMenu()
            .enqueue(object : Callback<StoreDetailData> {
                override fun onResponse(
                    call: Call<StoreDetailData>,
                    response: Response<StoreDetailData>
                ) {
                    if (response.isSuccessful) {
                        _responseResult.value = response.body()
                    } else {
                        _errorResult.value = response.message()
                    }
                }

                override fun onFailure(call: Call<StoreDetailData>, t: Throwable) {
                    _errorResult.value = t.toString()
                }
            })
    }
}