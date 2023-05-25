package sopt.haeti.baeminaos.presentation.storedetail

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout.VERTICAL
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import sopt.haeti.baeminaos.R
import sopt.haeti.baeminaos.data.remote.CartItemAddRequestDto
import sopt.haeti.baeminaos.data.remote.StoreDetailData
import sopt.haeti.baeminaos.databinding.ActivityStoreDetailBinding
import sopt.haeti.baeminaos.presentation.cart.CartActivity
import sopt.haeti.baeminaos.presentation.home.HomeActivity
import sopt.haeti.baeminaos.presentation.store.StoreActivity
import sopt.haeti.baeminaos.presentation.storedetail.adapter.OptionRVAdapter
import sopt.haeti.baeminaos.util.base.BindingActivity
import java.text.DecimalFormat
import kotlin.properties.Delegates

class StoreDetailActivity :
    BindingActivity<ActivityStoreDetailBinding>(R.layout.activity_store_detail), TotalPrice,
    TotalOption {

    private lateinit var optionRVAdapter: OptionRVAdapter

    var count = 1
    lateinit var imageUri: String
    lateinit var totalOption: String

    private val storeDetailViewModel by viewModels<StoreDetailViewModel>()
    private val cartItemAddViewModel by viewModels<CartItemAddViewModel>()

    var data = arrayListOf<StoreDetailData.Data.OptionCategories>()
    var basePrice by Delegates.notNull<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //서버에서 메뉴 정보 가져오기
        getMenuDate()

        //장바구니에 담을 총 개수
        clickCount()

        //화면 이동
        navigateToActivity()

        //recyclerview divider
        val decoration = DividerItemDecoration(this, VERTICAL)

        optionRVAdapter = OptionRVAdapter(this)
        binding.rvOption.apply {
            layoutManager = LinearLayoutManager(this@StoreDetailActivity)
            adapter = optionRVAdapter
            addItemDecoration(decoration)
        }

    }

    override fun calcTotalPrice(price: Int) {
        val basePrice = binding.tvAddPrice.text.replace("[^0-9]".toRegex(), "").toInt()
        val totalPrice = basePrice + price
        binding.tvAddPrice.text = moneyFormat(totalPrice)
    }

    override fun addOption(option: String) {
        totalOption += ", $option"
    }

    //서버에서 메뉴 정보 가져오기
    private fun getMenuDate() {

        storeDetailViewModel.getMenuFromServer()

        storeDetailViewModel.responseResult.observe(this) { responseResult ->
            binding.tvMenuName.text = responseResult.data.name
            binding.tvMenuDescription.text = responseResult.data.description
            basePrice = responseResult.data.basePrice
            binding.tvBasePrice.text = moneyFormat(responseResult.data.basePrice)
            imageUri = responseResult.data.image
            binding.ivMenu.load(responseResult.data.image)
            binding.tvAddPrice.text = moneyFormat(responseResult.data.basePrice)
            responseResult.data.optionCategories.let {
                optionRVAdapter.submitList(it)
                Log.e("optionCategories", it.toString())
            }
        }
    }

    //cartId가 뭐임?
    private fun addCartItem() {
        val cartItemAddRequest = CartItemAddRequestDto(
            1,
            1,
            binding.tvMenuName.text.toString(),
            binding.ivMenu.toString(),
            binding.tvAddPrice.text.replace("[^0-9]".toRegex(), "").toInt(),
            totalOption,
            binding.tvCount.text.replace("[^0-9]".toRegex(), "").toInt()
        )

        cartItemAddViewModel.addCartItem(cartItemAddRequest)

//        cartItemAddViewModel.responseResult.observe(this) { responseResult ->
//            val cartItemId = responseResult.data.cartItemId
//            val name = responseResult.data.name
//            val count = responseResult.data.count
//            val price = responseResult.data.price
//        }
    }

    private fun clickCount() {
        binding.tvCount.text = "${count}개"

        binding.btnPlus.setOnClickListener {
            count += 1
            binding.tvCount.text = "${count}개"
            binding.btnMinus.setColorFilter(Color.parseColor("#000000"))
            val price = binding.tvAddPrice.text.replace("[^0-9]".toRegex(), "").toInt()
            val totalPrice = price + basePrice
            binding.tvAddPrice.text = moneyFormat(totalPrice)

        }

        binding.btnMinus.setOnClickListener {
            if (count > 1) {
                count -= 1
                binding.tvCount.text = "${count}개"
                val price = binding.tvAddPrice.text.replace("[^0-9]".toRegex(), "").toInt()
                val totalPrice = price - basePrice
                binding.tvAddPrice.text = moneyFormat(totalPrice)
                if (count == 1) binding.btnMinus.setColorFilter(Color.parseColor("#D9D9D9"))
            }
        }
    }

    private fun navigateToActivity() {
        binding.toolbar.btnBack.setOnClickListener {
            startActivity(Intent(this, StoreActivity::class.java))
        }
        binding.toolbar.btnHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        binding.toolbar.btnShopping.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
        binding.btnAddPrice.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
            addCartItem()
        }
    }

    private fun moneyFormat(money: Int): String {
        val moneyFormat = DecimalFormat("#,###")
        return moneyFormat.format(money) + "원"
    }
}