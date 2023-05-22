package sopt.haeti.baeminaos.presentation.storedetail

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout.VERTICAL
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import sopt.haeti.baeminaos.R
import sopt.haeti.baeminaos.data.local.StoreDetailOptionDataClass
import sopt.haeti.baeminaos.databinding.ActivityHomeBinding
import sopt.haeti.baeminaos.databinding.ActivityStoreDetailBinding
import sopt.haeti.baeminaos.presentation.cart.CartActivity
import sopt.haeti.baeminaos.presentation.home.HomeActivity
import sopt.haeti.baeminaos.presentation.store.StoreActivity
import sopt.haeti.baeminaos.presentation.storedetail.adapter.OptionRVAdapter
import sopt.haeti.baeminaos.util.base.BindingActivity
import timber.log.Timber

class StoreDetailActivity : BindingActivity<ActivityStoreDetailBinding>(R.layout.activity_store_detail) {

    private lateinit var optionRVAdapter : OptionRVAdapter
    var count = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setTotalCount()

        navigateToActivity()

        val data = mutableListOf(
            StoreDetailOptionDataClass(
                "옵션 명",
                "최대 6개 추가"
            ),
            StoreDetailOptionDataClass(
                "옵션 명",
                "최대 6개 추가"
            ),
            StoreDetailOptionDataClass(
                "옵션 명",
                "최대 6개 추가"
            ),
        )

        //recyclerview divider
        val decoration = DividerItemDecoration(this, VERTICAL)

        optionRVAdapter = OptionRVAdapter()
        optionRVAdapter.submitList(data)
        binding.rvOption.apply {
            layoutManager = LinearLayoutManager(this@StoreDetailActivity)
            adapter = optionRVAdapter
            addItemDecoration(decoration)
        }

    }

    private fun setTotalCount() {
        binding.tvCount.text = "${count}개"

        binding.btnPlus.setOnClickListener {
        count += 1
        binding.tvCount.text = "${count}개"
        binding.btnMinus.setColorFilter(Color.parseColor("#000000"))
        }

        binding.btnMinus.setOnClickListener {
            if(count > 1) {
                count -= 1
                binding.tvCount.text = "${count}개"
                if(count == 1) binding.btnMinus.setColorFilter(Color.parseColor("#D9D9D9"))
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
    }
}