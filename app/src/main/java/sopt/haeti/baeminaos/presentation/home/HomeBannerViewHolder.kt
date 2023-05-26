package sopt.haeti.baeminaos.presentation.home

import androidx.recyclerview.widget.RecyclerView
import sopt.haeti.baeminaos.databinding.ItemMainBannerBinding

class HomeBannerViewHolder(val binding: ItemMainBannerBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(src: Int) {
        binding.imgMainBanner.setImageResource(src)
    }
}