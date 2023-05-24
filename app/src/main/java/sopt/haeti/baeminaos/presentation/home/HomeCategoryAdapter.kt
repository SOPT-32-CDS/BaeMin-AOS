package sopt.haeti.baeminaos.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import sopt.haeti.baeminaos.data.local.MockCategoryDataClass
import sopt.haeti.baeminaos.databinding.ItemMainCategoryBinding
import sopt.haeti.baeminaos.util.ItemDiffCallback

class HomeCategoryAdapter :
    ListAdapter<MockCategoryDataClass, HomeCategoryViewHolder>(
        ItemDiffCallback<MockCategoryDataClass>(onContentsTheSame = { old, new -> old == new },
            onItemsTheSame = { old, new -> old == new })
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoryViewHolder {
        val binding: ItemMainCategoryBinding =
            ItemMainCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeCategoryViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}