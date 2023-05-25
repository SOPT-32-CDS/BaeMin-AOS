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
    context: Context
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
        holder.onBind(getItem(position))
    }


    class StoreMenuViewHolder(
        private val binding: ItemStoreMenuBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: StoreMenu) {
            binding.tvMenu.text = item.menuName
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                val show = toggleLayout(!item.isExpanded, it, binding.rvMenuDetail)
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