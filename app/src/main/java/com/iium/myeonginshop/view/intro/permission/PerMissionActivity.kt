package com.iium.myeonginshop.view.intro.permission

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.databinding.DataBindingUtil
import com.iium.myeonginshop.R
import com.iium.myeonginshop.databinding.ActivityPerMissionBinding
import com.iium.myeonginshop.util.`object`.Constant
import com.iium.myeonginshop.util.`object`.Constant.PREF_PERMISSION_GRANTED
import com.iium.myeonginshop.util.activity.ActivityControlManager.moveActivity
import com.iium.myeonginshop.util.activity.MyApplication.Companion.prefs
import com.iium.myeonginshop.util.base.BaseActivity
import com.iium.myeonginshop.view.intro.splash.SplashActivity

class PerMissionActivity : BaseActivity() {

    private lateinit var binding: ActivityPerMissionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_per_mission)
        binding.activity = this
        inStatusBar()
    }

    fun onPerOkClick(v: View) {
        if (!PermissionManager.getAllPermissionGranted(this)) {
            PermissionManager.requestAllPermissions(this)
        } else {
            prefs.setBool(PREF_PERMISSION_GRANTED, true)
            moveActivity(b = true)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constant.ALL_PERMISSION_REQUEST_CODE) {
            if (PermissionManager.getAllPermissionGranted(this)) {
                prefs.setBool(Constant.PREF_PERMISSION_GRANTED, true)
                moveActivity(b = true)
            }
            else {
                if (getPermissionAllGranted()) {
                }
                else {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                }
            }
        }
    }

    private fun getPermissionAllGranted(): Boolean {
        var permissonNotShowDenied = true
        for (permission in Constant.MUTILE_PERMISSION) {
            if (!permission?.let {
                    shouldShowRequestPermissionRationale(it)
                }!!) {
                permissonNotShowDenied = false
            }
        }
        return permissonNotShowDenied
    }

    private fun moveActivity(b: Boolean) {
        val intent = Intent(this, SplashActivity::class.java)
        startActivity(intent)
        overridePendingTransition(0, 0)
        finish()
    }
}