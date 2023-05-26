package sopt.haeti.baeminaos.presentation.store

import android.os.Bundle
import sopt.haeti.baeminaos.R
import sopt.haeti.baeminaos.data.local.StoreMenu
import sopt.haeti.baeminaos.data.local.StoreMenuDetail
import sopt.haeti.baeminaos.databinding.ActivityStoreBinding
import sopt.haeti.baeminaos.util.base.BindingActivity

class StoreActivity : BindingActivity<ActivityStoreBinding>(R.layout.activity_store) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setAdapter()
    }

    private fun setAdapter() {
        val storeMenuList = generateData()
        val adapter = StoreExpandableAdapter(this, storeMenuList)
        adapter.submitList(storeMenuList)
        binding.rvMenu.adapter = adapter
    }

    private fun generateData(): List<StoreMenu> {
        val storeMenuList = mutableListOf<StoreMenu>()
        for (i in 0..5) {

            val innerItemList = mutableListOf<StoreMenuDetail>()
            for (j in 0..2) {
                innerItemList.add(
                    j, StoreMenuDetail(
                        "[재주문 1위] 특초밥+미...",
                        "흰살생선3p, 연어 2p, 참치1p, 황새치 1p,\n초새우1p, 간장새우1p, 생새우1p, 소...",
                        16000, "https://i.ibb.co/ydXz7jN/menu.png"
                    )
                )
            }
            storeMenuList.add(StoreMenu("인기 메뉴", false, innerItemList))
        }

        return storeMenuList

    }
}