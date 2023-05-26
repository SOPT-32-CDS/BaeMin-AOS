package sopt.haeti.baeminaos.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sopt.haeti.baeminaos.data.remote.CartCountRequestDto
import sopt.haeti.baeminaos.data.remote.CartCountResponseDto
import sopt.haeti.baeminaos.module.ServicePool

class CartCountViewModel : ViewModel() {
    private val _responseResult: MutableLiveData<CartCountResponseDto> = MutableLiveData()
    val responseResult: LiveData<CartCountResponseDto> = _responseResult

    private val _errorResult: MutableLiveData<String> = MutableLiveData()
    val errorResult: LiveData<String> = _errorResult

    fun updateCountToServer(cartItemId: Int, count: Int) {
        ServicePool.cartService.updateCount(
            CartCountRequestDto(
                cartItemId, count
            )
        )
            .enqueue(object : Callback<CartCountResponseDto> {
                override fun onResponse(
                    call: Call<CartCountResponseDto>,
                    response: Response<CartCountResponseDto>
                ) {
                    if (response.isSuccessful) {
                        _responseResult.value = response.body()
                    } else {
                        _errorResult.value = response.message()
                    }
                }

                override fun onFailure(call: Call<CartCountResponseDto>, t: Throwable) {
                    _errorResult.value = t.toString()
                }
            })
    }
}