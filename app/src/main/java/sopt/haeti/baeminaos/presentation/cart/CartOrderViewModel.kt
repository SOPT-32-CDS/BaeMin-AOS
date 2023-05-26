package sopt.haeti.baeminaos.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sopt.haeti.baeminaos.data.remote.CartOrderResponseDto
import sopt.haeti.baeminaos.module.ServicePool

class CartOrderViewModel : ViewModel() {
    private val _responseResult: MutableLiveData<CartOrderResponseDto> = MutableLiveData()
    val responseResult: LiveData<CartOrderResponseDto> = _responseResult

    private val _errorResult: MutableLiveData<String> = MutableLiveData()
    val errorResult: LiveData<String> = _errorResult

    fun orderItemToServer(cartId : Int) {
        ServicePool.cartService.order(cartId)
            .enqueue(object : Callback<CartOrderResponseDto> {
                override fun onResponse(
                    call: Call<CartOrderResponseDto>,
                    response: Response<CartOrderResponseDto>
                ) {
                    if (response.isSuccessful) {
                        _responseResult.value = response.body()
                    } else {
                        _errorResult.value = response.message()
                    }
                }

                override fun onFailure(call: Call<CartOrderResponseDto>, t: Throwable) {
                    _errorResult.value = t.toString()
                }
            })
    }
}