package com.iium.myeonginshop.view.intro.splash

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.iium.myeonginshop.R
import com.iium.myeonginshop.databinding.ActivitySplashBinding
import com.iium.myeonginshop.util.`object`.Constant
import com.iium.myeonginshop.util.activity.ActivityControlManager
import com.iium.myeonginshop.util.base.BaseActivity
import com.iium.myeonginshop.util.log.LLog
import com.iium.myeonginshop.view.main.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {

    private lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)
        binding.activity = this
        inStatusBar()
        checkNetwork()
    }

    // 네트워크 체크
    private fun checkNetwork() {
        LLog.e("1. 네트워크 확인")
        if(isInternetAvailable(this)) {
            moveLogin()
        } else {
            networkDialog()
            return
        }
    }

    private fun moveLogin() {
        LLog.e("11. 로그인 페이지 이동")
        ActivityControlManager.delayRun(
            { moveAndFinishActivity(MainActivity::class.java) },
            Constant.SPLASH_WAIT
        )
    }
}