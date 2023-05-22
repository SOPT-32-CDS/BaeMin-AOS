package sopt.haeti.baeminaos.presentation.storedetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sopt.haeti.baeminaos.data.local.StoreDetailChoiceDataClass
import sopt.haeti.baeminaos.databinding.ItemStoreChoiceBinding

class ChoiceRVAdapter() : ListAdapter<StoreDetailChoiceDataClass, ChoiceRVAdapter.ViewHolder>(diffUtil){

    class ViewHolder(private val binding : ItemStoreChoiceBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: StoreDetailChoiceDataClass) {
            binding.tvName.text = item.title
            binding.tvPrice.text = "+${item.price}원"

            //아이템 클릭 시 체크박스 상태 바뀜
            binding.root.setOnClickListener {
                binding.checkbox.isChecked = !binding.checkbox.isChecked

                if(binding.checkbox.isChecked) {

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemStoreChoiceBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<StoreDetailChoiceDataClass>() {

            override fun areItemsTheSame(
                oldItem: StoreDetailChoiceDataClass,
                newItem: StoreDetailChoiceDataClass
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: StoreDetailChoiceDataClass,
                newItem: StoreDetailChoiceDataClass
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}