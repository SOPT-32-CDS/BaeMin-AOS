package sopt.haeti.baeminaos.presentation.storedetail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sopt.haeti.baeminaos.data.local.StoreDetailChoiceDataClass
import sopt.haeti.baeminaos.data.local.StoreDetailOptionDataClass
import sopt.haeti.baeminaos.databinding.ItemStoreOptionBinding

class OptionRVAdapter() : ListAdapter<StoreDetailOptionDataClass, OptionRVAdapter.ViewHolder>(diffUtil) {

    class ViewHolder(private val binding: ItemStoreOptionBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(item : StoreDetailOptionDataClass) {

                binding.tvName.text = item.title
                binding.tvDescription.text = item.maxCount

                val choiceAdapter = ChoiceRVAdapter()

                val data = mutableListOf(
                    StoreDetailChoiceDataClass(
                        "옵션1",
                        7000
                    ),
                    StoreDetailChoiceDataClass(
                        "옵션1",
                        7000
                    ),
                    StoreDetailChoiceDataClass(
                        "옵션1",
                        7000
                    ),
                )

                choiceAdapter.submitList(data)
                binding.rvChoice.apply {
                    layoutManager = LinearLayoutManager(binding.rvChoice.context)
                    adapter = choiceAdapter
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemStoreOptionBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<StoreDetailOptionDataClass>() {

            override fun areItemsTheSame(
                oldItem: StoreDetailOptionDataClass,
                newItem: StoreDetailOptionDataClass
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: StoreDetailOptionDataClass,
                newItem: StoreDetailOptionDataClass
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}