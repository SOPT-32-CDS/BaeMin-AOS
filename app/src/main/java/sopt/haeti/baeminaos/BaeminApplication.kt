package sopt.haeti.baeminaos

import android.app.Application
import androidx.databinding.ktx.BuildConfig
import timber.log.Timber

class BaeminApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}
