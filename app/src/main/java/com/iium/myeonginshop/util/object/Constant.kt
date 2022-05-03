package com.iium.myeonginshop.util.`object`

import android.Manifest

import java.util.ArrayList

object  Constant {
    const val TAG = "MywongIn_Shop"
    const val ONE_PERMISSION_REQUEST_CODE = 1
    const val ALL_PERMISSION_REQUEST_CODE = 2
    const val PREF_PERMISSION_GRANTED = "PREF_PERMISSION_GRANTED"

    const val BACKPRESS_CLOSE_TIME = 1500
    const val ALBUM_REQUEST_CODE = 302
    const val DEFATULT_TIMEOUT = 20000

    // Splash
    const val SPLASH_WAIT = 2500

    val MUTILE_PERMISSION: ArrayList<String?> = object : ArrayList<String?>() {
        init {
            add(Manifest.permission.ACCESS_FINE_LOCATION)
            add(Manifest.permission.ACCESS_COARSE_LOCATION)
            add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            add(Manifest.permission.READ_EXTERNAL_STORAGE)
            add(Manifest.permission.READ_PHONE_STATE)
            add(Manifest.permission.READ_PHONE_NUMBERS)
        }
    }

    val PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION
        , Manifest.permission.ACCESS_COARSE_LOCATION
    )

}