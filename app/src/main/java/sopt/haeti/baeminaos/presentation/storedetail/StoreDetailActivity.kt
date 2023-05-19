package sopt.haeti.baeminaos.presentation.storedetail

import android.os.Bundle
import android.widget.LinearLayout.VERTICAL
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import sopt.haeti.baeminaos.R
import sopt.haeti.baeminaos.data.local.StoreDetailOptionDataClass
import sopt.haeti.baeminaos.databinding.ActivityHomeBinding
import sopt.haeti.baeminaos.databinding.ActivityStoreDetailBinding
import sopt.haeti.baeminaos.presentation.storedetail.adapter.OptionRVAdapter
import sopt.haeti.baeminaos.util.base.BindingActivity

class StoreDetailActivity : BindingActivity<ActivityStoreDetailBinding>(R.layout.activity_store_detail) {

    private lateinit var optionRVAdapter : OptionRVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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

        val decoration = DividerItemDecoration(this, VERTICAL)

        optionRVAdapter = OptionRVAdapter()
        optionRVAdapter.submitList(data)
        binding.rvOption.apply {
            layoutManager = LinearLayoutManager(this@StoreDetailActivity)
            adapter = optionRVAdapter
            addItemDecoration(decoration)
        }
    }
}