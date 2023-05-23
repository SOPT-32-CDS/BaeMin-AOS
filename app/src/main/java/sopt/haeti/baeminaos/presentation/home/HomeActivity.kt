package sopt.haeti.baeminaos.presentation.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import sopt.haeti.baeminaos.R
import sopt.haeti.baeminaos.data.local.mockBannerItemList
import sopt.haeti.baeminaos.databinding.ActivityHomeBinding
import sopt.haeti.baeminaos.util.base.BindingActivity
import timber.log.Timber

class HomeActivity : BindingActivity<ActivityHomeBinding>(R.layout.activity_home) {
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
        binding.viewPagerMainBanner.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tvMainIndicatorCurrent.text = (position + 1).toString()
            }
        })
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
}