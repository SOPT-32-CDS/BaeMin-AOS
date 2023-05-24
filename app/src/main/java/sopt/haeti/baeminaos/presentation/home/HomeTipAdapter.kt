package sopt.haeti.baeminaos.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import sopt.haeti.baeminaos.databinding.ItemMainTipBinding
import sopt.haeti.baeminaos.util.ItemDiffCallback

class HomeTipAdapter :
        ListAdapter<String, HomeTipViewHolder>(
            ItemDiffCallback<String>(onContentsTheSame = { old, new -> old == new },
                onItemsTheSame = { old, new -> old == new })
        ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTipViewHolder {
        val binding: ItemMainTipBinding =
            ItemMainTipBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeTipViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeTipViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}