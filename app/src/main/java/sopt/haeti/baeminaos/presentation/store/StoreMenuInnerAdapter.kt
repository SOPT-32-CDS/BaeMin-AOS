package sopt.haeti.baeminaos.presentation.store

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import sopt.haeti.baeminaos.R
import sopt.haeti.baeminaos.data.local.StoreMenuDetail
import sopt.haeti.baeminaos.databinding.ItemStoreMenuDetailBinding
import sopt.haeti.baeminaos.util.ItemDiffCallback
import java.text.DecimalFormat

class StoreMenuInnerAdapter(
    private val storeMenuInnerList: List<StoreMenuDetail>,
    context: Context
) :
    ListAdapter<StoreMenuDetail, StoreMenuInnerAdapter.StoreMenuInnerViewHolder>(ItemDiffCallback<StoreMenuDetail>(
        onItemsTheSame = { old, new -> old.menuDetailName == new.menuDetailName },
        onContentsTheSame = { old, new -> old == new }
    )) {

    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreMenuInnerViewHolder {
        val binding = ItemStoreMenuDetailBinding.inflate(inflater, parent, false)

        return StoreMenuInnerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreMenuInnerViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


    class StoreMenuInnerViewHolder(private val binding: ItemStoreMenuDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: StoreMenuDetail) {
            binding.tvMenuDetailName.text = item.menuDetailName
            binding.ivBestMenu.load(R.drawable.ic_best_menu)
            binding.tvBestMenuDesc.text = item.menuDescription
            binding.tvBestMenuPrice.text = moneyFormat(item.menuPrice)
            binding.ivMenuDetail.load(item.menuImage)
            binding.executePendingBindings()

        }

        private fun moneyFormat(money: Int): String {
            val moneyFormat = DecimalFormat("#,###")
            return moneyFormat.format(money) + "원"
        }
    }


}