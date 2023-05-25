package sopt.haeti.baeminaos.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import sopt.haeti.baeminaos.data.local.StoreListDataClass
import sopt.haeti.baeminaos.databinding.ItemMainHorizontalListBinding
import sopt.haeti.baeminaos.util.ItemDiffCallback

class HomeHorizontalAdapter :
    ListAdapter<StoreListDataClass, HomeHorizontalViewHolder>(
        ItemDiffCallback<StoreListDataClass>(onContentsTheSame = { old, new -> old == new },
            onItemsTheSame = { old, new -> old == new })
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHorizontalViewHolder {
        val binding: ItemMainHorizontalListBinding =
            ItemMainHorizontalListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeHorizontalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeHorizontalViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
    }
