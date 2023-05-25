package sopt.haeti.baeminaos.presentation.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sopt.haeti.baeminaos.data.remote.CartItemResponseDto
import sopt.haeti.baeminaos.module.ServicePool.cartService

class CartViewModel : ViewModel() {
    private val _responseResult: MutableLiveData<CartItemResponseDto> = MutableLiveData()
    val responseResult: LiveData<CartItemResponseDto> = _responseResult

    private val _errorResult: MutableLiveData<String> = MutableLiveData()
    val errorResult: LiveData<String> = _errorResult

    fun getListFromServer() {
        cartService.getList()
            .enqueue(object : Callback<CartItemResponseDto> {
                override fun onResponse(
                    call: Call<CartItemResponseDto>,
                    response: Response<CartItemResponseDto>
                ) {
                    if (response.isSuccessful) {
                        _responseResult.value = response.body()
                    } else {
                        _errorResult.value = response.message()
                    }
                }

                override fun onFailure(call: Call<CartItemResponseDto>, t: Throwable) {
                    _errorResult.value = t.toString()
                }
            })
    }
}