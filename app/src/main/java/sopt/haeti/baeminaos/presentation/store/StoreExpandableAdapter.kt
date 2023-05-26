package sopt.haeti.baeminaos.presentation.store

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sopt.haeti.baeminaos.data.remote.StoreDetailResponseDto
import sopt.haeti.baeminaos.databinding.ItemStoreMenuBinding
import sopt.haeti.baeminaos.util.ItemDiffCallback

class StoreExpandableAdapter(
    context: Context, private val storeMenu: List<StoreDetailResponseDto.Data.MenuCategory>
) : ListAdapter<StoreDetailResponseDto.Data.MenuCategory, StoreExpandableAdapter.StoreMenuViewHolder>(
    ItemDiffCallback<StoreDetailResponseDto.Data.MenuCategory>(
        onItemsTheSame = { old, new -> old.id == new.id },
        onContentsTheSame = { old, new -> old == new })
) {

    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreMenuViewHolder {
        val binding = ItemStoreMenuBinding.inflate(inflater, parent, false)
        return StoreMenuViewHolder(binding)
    }


    override fun onBindViewHolder(holder: StoreMenuViewHolder, position: Int) {
        val context = holder.itemView.context
        val innerAdapter = StoreMenuInnerAdapter(storeMenu[position].menus, context)
        holder.onBind(getItem(position), innerAdapter)
    }


    class StoreMenuViewHolder(
        private val binding: ItemStoreMenuBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            item: StoreDetailResponseDto.Data.MenuCategory,
            innerAdapter: StoreMenuInnerAdapter
        ) {
            val decoration =
                DividerItemDecoration(binding.rvMenuDetail.context, LinearLayout.VERTICAL)

            binding.rvMenuDetail.layoutManager = LinearLayoutManager(binding.rvMenuDetail.context)
            binding.tvMenu.text = item.name
            binding.rvMenuDetail.adapter = innerAdapter
            binding.rvMenuDetail.addItemDecoration(decoration)
            binding.executePendingBindings()
            innerAdapter.submitList(item.menus)

            binding.root.setOnClickListener {
                val show = toggleLayout(!item.isExpanded, binding.ivArrow, binding.rvMenuDetail)
                item.isExpanded = show
            }
        }

        private fun toggleLayout(
            isExpanded: Boolean,
            view: View,
            layoutExpand: RecyclerView
        ): Boolean {
            ToggleAnimation.toggleArrow(view, isExpanded)
            if (isExpanded) {
                ToggleAnimation.expand(layoutExpand)
            } else {
                ToggleAnimation.collapse(layoutExpand)
            }
            return isExpanded
        }
    }

}