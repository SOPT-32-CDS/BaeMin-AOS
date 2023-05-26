package sopt.haeti.baeminaos.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import sopt.haeti.baeminaos.R
import sopt.haeti.baeminaos.data.local.mockBannerItemList
import sopt.haeti.baeminaos.data.local.mockCartegoryItemList
import sopt.haeti.baeminaos.data.local.mockStoreList
import sopt.haeti.baeminaos.data.local.mockTipTextList
import sopt.haeti.baeminaos.databinding.ActivityHomeBinding
import sopt.haeti.baeminaos.presentation.store.StoreActivity
import sopt.haeti.baeminaos.util.base.BindingActivity
import timber.log.Timber

class HomeActivity : BindingActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private var storeListAdapter: HomeStoreAdapter? = null
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 툴바 설정
        setSupportActionBar(binding.toolbarMain)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // 배너 아이템 설정
        binding.viewPagerMainBanner.adapter = HomeBannerAdapter().apply {
            submitList(mockBannerItemList)
        }

        // 배너 인디케이터 설정
        binding.tvMainIndicatorTotal.text = mockBannerItemList.size.toString()
        binding.viewPagerMainBanner.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tvMainIndicatorCurrent.text = (position + 1).toString()
            }
        })

        setAdapter()

        binding.rvStore.setOnClickListener {
            Timber.e("가게리스트 클릭")
            startActivity(Intent(this, StoreActivity::class.java))
        }

    }

    // 툴바에 아이템 설정
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cart_toolbar, menu)
        return true
    }

    // 툴바 아이템 클릭 시 액션 설정
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_cart_toolbar_home -> {
                Timber.d("툴바 메인화면 버튼 클릭")
            }

            R.id.menu_cart_toolbar_person_plus -> {
                //도움말 버튼 눌렀을 때
                Timber.d("툴바 친구추가 버튼 클릭")
            }

            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun setAdapter() {
        // 팁 아이템 설정
        binding.rcvMainTip.adapter = HomeTipAdapter().apply {
            submitList(mockTipTextList)
        }

        // 카테고리 아이템 설정
        binding.rcvMainCategory.adapter = HomeCategoryAdapter().apply {
            submitList(mockCartegoryItemList)
        }

        // 가로 가게리스트 설정
        binding.rcvMainHorizontal.adapter = HomeHorizontalAdapter().apply {
            submitList(mockStoreList)
        }

        // 세로 가게리스트 설정

        viewModel.storeList.flowWithLifecycle(lifecycle).onEach { storeList ->
            storeListAdapter = HomeStoreAdapter(this).apply {
                submitList(storeList)
            }

            binding.rvStore.apply {
                layoutManager = LinearLayoutManager(this@HomeActivity)
                adapter = storeListAdapter
            }
        }.launchIn(lifecycleScope)
    }
}