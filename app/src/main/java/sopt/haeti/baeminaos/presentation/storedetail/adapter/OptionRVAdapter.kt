package sopt.haeti.baeminaos.presentation.storedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sopt.haeti.baeminaos.data.remote.StoreDetailData
import sopt.haeti.baeminaos.databinding.ItemStoreOptionBinding
import sopt.haeti.baeminaos.presentation.storedetail.TotalOption
import sopt.haeti.baeminaos.presentation.storedetail.TotalPrice

class OptionRVAdapter(val totalPrice: TotalPrice, val totalOption: TotalOption) :
    ListAdapter<StoreDetailData.Data.OptionCategories, OptionRVAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemStoreOptionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: StoreDetailData.Data.OptionCategories) {

            binding.tvName.text = item.name
            binding.tvDescription.text = item.description

            //옵션 선택 사항 recyclerView
            val choiceAdapter = ChoiceRVAdapter(totalPrice, totalOption)

            item.options.let {
                choiceAdapter.submitList(it)
            }

            binding.rvChoice.apply {
                layoutManager = LinearLayoutManager(binding.rvChoice.context)
                adapter = choiceAdapter
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemStoreOptionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<StoreDetailData.Data.OptionCategories>() {

            override fun areItemsTheSame(
                oldItem: StoreDetailData.Data.OptionCategories,
                newItem: StoreDetailData.Data.OptionCategories
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: StoreDetailData.Data.OptionCategories,
                newItem: StoreDetailData.Data.OptionCategories
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}