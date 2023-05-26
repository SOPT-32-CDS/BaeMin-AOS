package sopt.haeti.baeminaos.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sopt.haeti.baeminaos.data.remote.CartDeleteResponseDto
import sopt.haeti.baeminaos.data.remote.CartItemResponseDto
import sopt.haeti.baeminaos.module.ServicePool

class CartDeleteViewModel  : ViewModel() {
    private val _responseResult: MutableLiveData<CartDeleteResponseDto> = MutableLiveData()
    val responseResult: LiveData<CartDeleteResponseDto> = _responseResult

    private val _errorResult: MutableLiveData<String> = MutableLiveData()
    val errorResult: LiveData<String> = _errorResult

    fun deleteItemFromServer(cartItemId: Int) {
        ServicePool.cartService.deleteCart(cartItemId)
            .enqueue(object : Callback<CartDeleteResponseDto> {
                override fun onResponse(
                    call: Call<CartDeleteResponseDto>,
                    response: Response<CartDeleteResponseDto>
                ) {
                    if (response.isSuccessful) {
                        _responseResult.value = response.body()
                    } else {
                        _errorResult.value = response.message()
                    }
                }

                override fun onFailure(call: Call<CartDeleteResponseDto>, t: Throwable) {
                    _errorResult.value = t.toString()
                }
            })
    }
}