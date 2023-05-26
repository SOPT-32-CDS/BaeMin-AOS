package sopt.haeti.baeminaos.presentation.store

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import sopt.haeti.baeminaos.R
import sopt.haeti.baeminaos.databinding.ActivityStoreBinding
import sopt.haeti.baeminaos.util.base.BindingActivity
import timber.log.Timber

class StoreActivity : BindingActivity<ActivityStoreBinding>(R.layout.activity_store) {

    private var storeAdapter: StoreExpandableAdapter? = null
    private val storeViewModel by viewModels<StoreViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setAdapter()
    }

    private fun setAdapter() {
        val decoration = DividerItemDecoration(this, LinearLayout.VERTICAL)
        Log.e("setAdapter", "setAdapter")

        storeViewModel.menuCategory.flowWithLifecycle(lifecycle).onEach { menuCategory ->
            Log.e("menuCategory", menuCategory.toString())
            Timber.e(menuCategory.toString())
            storeAdapter = StoreExpandableAdapter(this, menuCategory.toMutableList())
            storeAdapter?.submitList(menuCategory.toMutableList())

            binding.rvMenu.apply {
                layoutManager = LinearLayoutManager(this@StoreActivity)
                adapter = storeAdapter
                addItemDecoration(decoration)
            }

        }.launchIn(lifecycleScope)
    }


}
