package sopt.haeti.baeminaos.presentation

import android.content.Intent
import android.os.Bundle
import sopt.haeti.baeminaos.R
import sopt.haeti.baeminaos.databinding.ActivityOrderCompleteBinding
import sopt.haeti.baeminaos.presentation.home.HomeActivity
import sopt.haeti.baeminaos.util.base.BindingActivity

class OrderCompleteActivity :
    BindingActivity<ActivityOrderCompleteBinding>(R.layout.activity_order_complete) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnOrderComplete.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }


}