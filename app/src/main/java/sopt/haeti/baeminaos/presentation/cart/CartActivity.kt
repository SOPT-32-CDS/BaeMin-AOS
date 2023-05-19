package sopt.haeti.baeminaos.presentation.cart

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import sopt.haeti.baeminaos.R
import sopt.haeti.baeminaos.data.local.CartItemData
import sopt.haeti.baeminaos.databinding.ActivityCartBinding
import sopt.haeti.baeminaos.util.base.BindingActivity
import timber.log.Timber
import java.text.DecimalFormat

class CartActivity : BindingActivity<ActivityCartBinding>(R.layout.activity_cart) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var itemOneCount = 0
        var itemOneTotalPrice = 0

        // 툴바 설정
        setSupportActionBar(binding.toolbarCart)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        // 툴바의 뒤로가기 버튼 설정
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)

        // 임시 데이터클래스 & 데이터 설정
        var mockCartItemData = CartItemData(1,"모듬초밥", 12000, "연어 10피스", 2)

        // 서버통신 못받으면 빈 리스트 말고 아예 틀 안보이도록 설정
        if (mockCartItemData == null) {
            with(binding) {
                viewCartItem1.visibility = View.GONE
                dividerCart2.visibility = View.GONE
            }
        }

        // 아이템 데이터 삽입
        with(binding) {
            tvCartItem1Title.text = mockCartItemData.itemName
            tvCartItem1Option.text = mockCartItemData.options
            itemOneCount = mockCartItemData.count
            tvCartItem1Number.text = itemOneCount.toString()
            itemOneTotalPrice = mockCartItemData.price * mockCartItemData.count
            tvCartItem1Price.text = moneyFormat(itemOneTotalPrice)
        }

        // 총 주문금액 변경
        with(binding) {
            val itemTotalPrice = itemOneTotalPrice + 26600
            tvCartDetailPrice.text = moneyFormat(itemTotalPrice)
            val itemTotalPriceWithTip = itemTotalPrice + 2000
            tvCartDetailTotalPrice.text = moneyFormat(itemTotalPriceWithTip)
            tvCartPurchasePrice.text = moneyFormat(itemTotalPriceWithTip)
        }

        // 삭제 버튼 구현
        binding.btnCartItem1Delete.setOnClickListener {
            // 여기에 서버 통신 DELETE 구현
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

    private fun moneyFormat(money: Int): String {
        val moneyFormat = DecimalFormat("#,###")
        val wonFormat = moneyFormat.format(money) + "원"
        return wonFormat
    }
}