package sopt.haeti.baeminaos.presentation.home

import androidx.recyclerview.widget.RecyclerView
import sopt.haeti.baeminaos.databinding.ItemMainTipBinding

class HomeTipViewHolder(val binding: ItemMainTipBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun onBind(tipText: String) {
                binding.tvMainTip.text = tipText
            }
        }
