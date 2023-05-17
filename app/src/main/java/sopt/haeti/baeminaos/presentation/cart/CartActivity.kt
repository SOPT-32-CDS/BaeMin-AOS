package sopt.haeti.baeminaos.presentation.cart

import android.os.Bundle
import android.view.Menu
import sopt.haeti.baeminaos.R
import sopt.haeti.baeminaos.databinding.ActivityCartBinding
import sopt.haeti.baeminaos.util.base.BindingActivity

class CartActivity : BindingActivity<ActivityCartBinding>(R.layout.activity_cart) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 툴바 설정
        setSupportActionBar(binding.toolbarCart)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        // 툴바의 뒤로가기 버튼 설정
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_back)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cart_toolbar, menu)
        return true
    }
}