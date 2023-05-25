package sopt.haeti.baeminaos.presentation.home

import androidx.recyclerview.widget.RecyclerView
import sopt.haeti.baeminaos.data.local.CategoryDataClass
import sopt.haeti.baeminaos.databinding.ItemMainCategoryBinding


class HomeCategoryViewHolder(val binding: ItemMainCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun onBind(item: CategoryDataClass) {
                binding.ivMainCategory.setImageDrawable(binding.root.context.getDrawable(item.image))
                binding.tvMainCategory.text = item.name
            }
        }
