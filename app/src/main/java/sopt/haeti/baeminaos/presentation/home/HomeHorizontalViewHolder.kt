package sopt.haeti.baeminaos.presentation.home

import androidx.recyclerview.widget.RecyclerView
import sopt.haeti.baeminaos.data.local.StoreListDataClass
import sopt.haeti.baeminaos.databinding.ItemMainHorizontalListBinding
import java.text.DecimalFormat

class HomeHorizontalViewHolder(val binding: ItemMainHorizontalListBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: StoreListDataClass) {
        binding.ivMainHorizontal.setImageDrawable(binding.root.context.getDrawable(item.image))
        binding.tvMainHorizontalStore.text = item.name
        binding.tvMainHorizontalRate.text = item.rate.toString()
        binding.tvMainHorizontalMinute.text =
            item.minDeliveryTime.toString() + "~" + item.maxDeliveryTime.toString() + "분"
        binding.tvMainHorizontalTip.text = moneyFormat(item.minDeliveryFee)
    }

    private fun moneyFormat(money: Int): String {
        val moneyFormat = DecimalFormat("#,###")
        return moneyFormat.format(money) + "원"
    }
}

