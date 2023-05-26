package sopt.haeti.baeminaos.presentation.storedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sopt.haeti.baeminaos.data.remote.CartItemAddRequestDto
import sopt.haeti.baeminaos.data.remote.CartItemAddResponseDto
import sopt.haeti.baeminaos.data.remote.StoreDetailData
import sopt.haeti.baeminaos.module.StoreDetailServicePool

class CartItemAddViewModel : ViewModel() {
    private val _responseResult: MutableLiveData<CartItemAddResponseDto> = MutableLiveData()
    val responseResult: LiveData<CartItemAddResponseDto> = _responseResult

    private val _errorResult: MutableLiveData<String> = MutableLiveData()
    val errorResult: LiveData<String> = _errorResult

    fun addCartItem(cartItemAddRequest : CartItemAddRequestDto) {
        StoreDetailServicePool.cartItemAddService.addCart(cartItemAddRequest)
            .enqueue(object : Callback<CartItemAddResponseDto> {
                override fun onResponse(
                    call: Call<CartItemAddResponseDto>,
                    response: Response<CartItemAddResponseDto>
                ) {
                    if (response.isSuccessful) {
                        _responseResult.value = response.body()
                    } else {
                        _errorResult.value = response.message()
                    }
                }

                override fun onFailure(call: Call<CartItemAddResponseDto>, t: Throwable) {
                    _errorResult.value = t.toString()
                }
            })
    }
}