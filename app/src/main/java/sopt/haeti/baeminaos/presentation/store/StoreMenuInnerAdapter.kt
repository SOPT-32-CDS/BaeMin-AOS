package sopt.haeti.baeminaos.presentation.store

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import sopt.haeti.baeminaos.R
import sopt.haeti.baeminaos.data.remote.StoreDetailResponseDto
import sopt.haeti.baeminaos.databinding.ItemStoreMenuDetailBinding
import sopt.haeti.baeminaos.presentation.storedetail.StoreDetailActivity
import sopt.haeti.baeminaos.util.ItemDiffCallback
import java.text.DecimalFormat

class StoreMenuInnerAdapter(
    private val storeMenuInnerList: List<StoreDetailResponseDto.Data.MenuCategory.Menu>,
    context: Context
) :
    ListAdapter<StoreDetailResponseDto.Data.MenuCategory.Menu, StoreMenuInnerAdapter.StoreMenuInnerViewHolder>(
        ItemDiffCallback<StoreDetailResponseDto.Data.MenuCategory.Menu>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )) {

    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreMenuInnerViewHolder {
        val binding = ItemStoreMenuDetailBinding.inflate(inflater, parent, false)

        return StoreMenuInnerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoreMenuInnerViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }


    class StoreMenuInnerViewHolder(private val binding: ItemStoreMenuDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: StoreDetailResponseDto.Data.MenuCategory.Menu) {
            binding.tvMenuDetailName.text = truncateText(item.name, 14)
            binding.ivBestMenu.load(R.drawable.ic_best_menu)
            binding.tvBestMenuDesc.text = wrapText(item.description, 28)
            binding.tvBestMenuPrice.text = moneyFormat(item.base_price)
            binding.ivMenuDetail.load(item.image)

            binding.root.setOnClickListener {
                binding.root.context.startActivity(Intent(it.context, StoreDetailActivity::class.java))
            }

            binding.executePendingBindings()

        }

        private fun truncateText(text: String, maxLength: Int): String {
            return if (text.length > maxLength) {
                text.substring(0, maxLength) + "..."
            } else {
                text
            }
        }

        private fun wrapText(text: String, maxChars: Int): String {
            val words = text.split(" ")
            val output = StringBuilder()
            var currentLineLength = 0

            for (word in words) {
                if (currentLineLength + word.length > maxChars) {
                    output.append("\n")
                    currentLineLength = 0
                }
                output.append(word)
                output.append(" ")
                currentLineLength += word.length + 1
            }

            return output.toString()
        }

        private fun moneyFormat(money: Int): String {
            val moneyFormat = DecimalFormat("#,###")
            return moneyFormat.format(money) + "원"
        }
    }


}