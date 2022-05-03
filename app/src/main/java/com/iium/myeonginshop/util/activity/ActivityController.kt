package com.iium.myeonginshop.util.activity

import android.content.Intent
import android.os.Bundle
import com.iium.myeonginshop.util.`object`.Constant
import com.iium.myeonginshop.util.activity.MyApplication.Companion.prefs
import com.iium.myeonginshop.util.base.BaseActivity
import com.iium.myeonginshop.view.intro.permission.PerMissionActivity
import com.iium.myeonginshop.view.intro.splash.SplashActivity

class ActivityController : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent

        if (intent != null) {
            val deeplinkUri = intent.data
            if (deeplinkUri != null && deeplinkUri.isHierarchical) {
                val deeplinkString = intent.dataString
                if (deeplinkString!!.contains("phone=")) {
                    val splitResult = deeplinkString.split("phone=").toTypedArray()
                    if (splitResult.size > 1) {
                    }
                }
            }
        }

        var next: Class<*> = SplashActivity::class.java
        if (!prefs.getBool(Constant.PREF_PERMISSION_GRANTED)) {
            next = PerMissionActivity::class.java
        }
        startActivity(Intent(this, next))
        finish()
    }
}