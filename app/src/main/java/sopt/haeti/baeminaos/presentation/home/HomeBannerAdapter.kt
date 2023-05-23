package sopt.haeti.baeminaos.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import sopt.haeti.baeminaos.databinding.ItemMainBannerBinding
import sopt.haeti.baeminaos.util.ItemDiffCallback

class HomeBannerAdapter :
    ListAdapter<Int, HomeBannerViewHolder>(
        ItemDiffCallback<Int>(onContentsTheSame = { old, new -> old == new },
            onItemsTheSame = { old, new -> old == new })
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBannerViewHolder {
        val binding: ItemMainBannerBinding =
            ItemMainBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeBannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeBannerViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}