package sopt.haeti.baeminaos.presentation.storedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sopt.haeti.baeminaos.data.remote.StoreDetailData
import sopt.haeti.baeminaos.databinding.ItemStoreChoiceBinding
import sopt.haeti.baeminaos.presentation.storedetail.TotalPrice
import java.text.DecimalFormat

class ChoiceRVAdapter(val totalPrice: TotalPrice) :
    ListAdapter<StoreDetailData.Data.OptionCategories.Options, ChoiceRVAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemStoreChoiceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: StoreDetailData.Data.OptionCategories.Options) {

            val moneyFormat = DecimalFormat("#,###")
            val itemPrice = moneyFormat.format(item.price) + "원"

            binding.tvName.text = item.name
            binding.tvPrice.text = "+$itemPrice"

            //아이템 클릭 시 체크박스 상태 바뀜
            binding.root.setOnClickListener {

                binding.checkbox.isChecked = !binding.checkbox.isChecked

                if (binding.checkbox.isChecked) {
                    totalPrice.calcTotalPrice(item.price)
                } else {
                    totalPrice.calcTotalPrice((item.price) * (-1))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemStoreChoiceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil =
            object : DiffUtil.ItemCallback<StoreDetailData.Data.OptionCategories.Options>() {

                override fun areItemsTheSame(
                    oldItem: StoreDetailData.Data.OptionCategories.Options,
                    newItem: StoreDetailData.Data.OptionCategories.Options
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: StoreDetailData.Data.OptionCategories.Options,
                    newItem: StoreDetailData.Data.OptionCategories.Options
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}