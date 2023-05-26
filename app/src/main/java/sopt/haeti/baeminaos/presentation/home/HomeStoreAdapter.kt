package sopt.haeti.baeminaos.presentation.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sopt.haeti.baeminaos.data.local.StoreList
import sopt.haeti.baeminaos.databinding.ItemStoreListBinding
import sopt.haeti.baeminaos.presentation.store.StoreActivity
import sopt.haeti.baeminaos.util.ItemDiffCallback

class HomeStoreAdapter(context: Context) :
    ListAdapter<StoreList, HomeStoreAdapter.StoreListViewHolder>(
        ItemDiffCallback<StoreList>(onContentsTheSame = { old, new -> old.id == new.id },
            onItemsTheSame = { old, new -> old == new })
    ) {

    private val inflater by lazy { LayoutInflater.from(context) }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreListViewHolder {
        val binding: ItemStoreListBinding =
            ItemStoreListBinding.inflate(inflater, parent, false)
        return StoreListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreListViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


    class StoreListViewHolder(private val binding: ItemStoreListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(data: StoreList) {
            binding.tvStoreName.text = data.name
            binding.tvMinOrder.text =
                data.min_delivery_time.toString() + "~" + data.max_delivery_time.toString() + "분"
            binding.tvStoreDeliveryTip.text = "배달팁" + data.delivery_fee + "원"
            binding.tvMinOrderPrice.text = data.min_order_amount.toString() + "원"

            binding.root.setOnClickListener {
                binding.root.context.startActivity(Intent(it.context, StoreActivity::class.java))
            }
        }
    }

}