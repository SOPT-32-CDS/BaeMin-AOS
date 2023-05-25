package sopt.haeti.baeminaos.presentation.store

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sopt.haeti.baeminaos.data.local.StoreMenu
import sopt.haeti.baeminaos.databinding.ItemStoreMenuBinding
import sopt.haeti.baeminaos.util.ItemDiffCallback

class StoreExpandableAdapter(
    context: Context, private val storeMenu: List<StoreMenu>
) : ListAdapter<StoreMenu, StoreExpandableAdapter.StoreMenuViewHolder>(
    ItemDiffCallback<StoreMenu>(
        onItemsTheSame = { old, new -> old.menuName == new.menuName },
        onContentsTheSame = { old, new -> old == new })
) {

    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreMenuViewHolder {
        val binding = ItemStoreMenuBinding.inflate(inflater, parent, false)
        return StoreMenuViewHolder(binding)
    }


    override fun onBindViewHolder(holder: StoreMenuViewHolder, position: Int) {
        val context = holder.itemView.context
        val innerAdapter = StoreMenuInnerAdapter(storeMenu[position].storeMenuDetail, context)
        holder.onBind(getItem(position), innerAdapter)
    }


    class StoreMenuViewHolder(
        private val binding: ItemStoreMenuBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: StoreMenu, innerAdapter: StoreMenuInnerAdapter) {
            binding.tvMenu.text = item.menuName
            binding.rvMenuDetail.adapter = innerAdapter
            binding.executePendingBindings()
            innerAdapter.submitList(item.storeMenuDetail)

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