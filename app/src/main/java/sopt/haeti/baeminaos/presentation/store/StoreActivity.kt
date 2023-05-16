package sopt.haeti.baeminaos.presentation.store

import android.os.Bundle
import sopt.haeti.baeminaos.R
import sopt.haeti.baeminaos.databinding.ActivityHomeBinding
import sopt.haeti.baeminaos.util.base.BindingActivity

class StoreActivity : BindingActivity<ActivityHomeBinding>(R.layout.activity_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}