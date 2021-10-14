package com.lutfiseptian.test.kanggo.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.lutfiseptian.test.kanggo.R
import com.lutfiseptian.test.kanggo.base.BaseActivity
import com.lutfiseptian.test.kanggo.databinding.ActivitySplashBinding
import com.lutfiseptian.test.kanggo.ui.main.MainActivity

class SplashActivity : BaseActivity() {

    private val binding  by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }
    override fun setLayout()=binding.root

    override fun onCreateActivity(savedInstanceState: Bundle?) {
        with(window) {
            setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        handlerDelay {
            openActivity(MainActivity::class.java,true)
        }
    }

}