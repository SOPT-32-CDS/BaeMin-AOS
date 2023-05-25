package sopt.haeti.baeminaos.presentation.cart

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import sopt.haeti.baeminaos.R
import sopt.haeti.baeminaos.data.remote.CartItemResponseDto
import sopt.haeti.baeminaos.databinding.ActivityCartBinding
import sopt.haeti.baeminaos.util.base.BindingActivity
import sopt.haeti.baeminaos.util.extension.visible
import timber.log.Timber
import java.text.DecimalFormat
import kotlin.properties.Delegates

class CartActivity : BindingActivity<ActivityCartBinding>(R.layout.activity_cart) {

    private var itemOneCount = 0
    private var itemOneTotalPrice = 0
    private var itemOnePrice = 0

    private val cartItemViewModel by viewModels<CartItemViewModel>()
    private val cartCountViewModel by viewModels<CartCountViewModel>()

    private var itemId by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 툴바 설정
        setSupportActionBar(binding.toolbarCart)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setToolbarWithBackIcon()

        // 장바구니에 아이템 담기
        cartItemViewModel.getListFromServer()

        cartItemViewModel.responseResult.observe(this) { responseResult ->
            // 아이템 데이터 삽입 & 금액 설정
            val firstCartStore = responseResult.data.cartStoreList[0]
            val firstCartItem = firstCartStore.cartItemList[0]
            itemId = firstCartItem.cartItemId
            setItem1Text(firstCartItem)
            setPrice(firstCartItem)
            setTotalPrice()
        }
        cartItemViewModel.errorResult.observe(this) { _ ->
            // 빈 리스트 말고 아예 틀 안보이도록 설정
            deleteItem1View()
        }

        // 수량 변경 버튼 구현
        with(binding) {
            btnCartItem1NumberPlus.setOnClickListener {
                itemOneCount += 1
                tvCartItem1Number.text = itemOneCount.toString()
                changePrice()
                cartCountViewModel.updateCountToServer(itemId,itemOneCount)
            }
            btnCartItem1NumberMinus.setOnClickListener {
                if (itemOneCount > 1) {
                    itemOneCount -= 1
                    tvCartItem1Number.text = itemOneCount.toString()
                    changePrice()
                    cartCountViewModel.updateCountToServer(itemId,itemOneCount)
                }
            }
        }

        // 삭제 버튼 구현
        binding.btnCartItem1Delete.setOnClickListener {
            // 여기에 서버 통신 DELETE 구현
            deleteItem1View()
        }
    }

    // 툴바에 아이템 설정
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cart_toolbar, menu)
        return true
    }

    // 툴바 아이템 클릭 시 액션 설정
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_cart_toolbar_home -> {
                Timber.d("툴바 메인화면 버튼 클릭")
            }
            R.id.menu_cart_toolbar_person_plus -> {
                //도움말 버튼 눌렀을 때
                Timber.d("툴바 친구추가 버튼 클릭")
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    // 금액 형식 설정
    private fun moneyFormat(money: Int): String {
        val moneyFormat = DecimalFormat("#,###")
        return moneyFormat.format(money) + "원"
    }

    // 수량에 따른 금액 설정
    private fun setPrice(item: CartItemResponseDto.Cart.CartStore.CartItem) {
        with(binding) {
            itemOneCount = item.count
            tvCartItem1Number.text = itemOneCount.toString()
            itemOneTotalPrice = item.totalPrice
            itemOnePrice = itemOneTotalPrice / itemOneCount
            tvCartItem1Price.text = moneyFormat(itemOneTotalPrice)
        }
    }

    // 총 주문금액 변경
    private fun setTotalPrice() {
        with(binding) {
            val itemTotalPrice = itemOneTotalPrice + 26600
            tvCartDetailPrice.text = moneyFormat(itemTotalPrice)
            val itemTotalPriceWithTip = itemTotalPrice + 2000
            tvCartDetailTotalPrice.text = moneyFormat(itemTotalPriceWithTip)
            tvCartPurchasePrice.text = moneyFormat(itemTotalPriceWithTip)
        }
    }

    // 수량 변경 적용한 금액 재설정
    private fun changePrice() {
        with(binding) {
            tvCartItem1Number.text = itemOneCount.toString()
            itemOneTotalPrice = itemOnePrice * itemOneCount
            tvCartItem1Price.text = moneyFormat(itemOneTotalPrice)
            setTotalPrice()
        }
    }

    private fun setToolbarWithBackIcon() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)
    }

    private fun setItem1Text(item: CartItemResponseDto.Cart.CartStore.CartItem) {
        with(binding) {
            tvCartItem1Menu.text = item.name
            tvCartItem1Option.text = item.options
        }
    }

    private fun deleteItem1View() {
        with(binding) {
            viewCartItem1.visible(false)
            dividerCart2.visible(false)
        }
    }
}