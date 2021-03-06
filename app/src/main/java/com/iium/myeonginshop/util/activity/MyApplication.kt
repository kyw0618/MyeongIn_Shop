package com.iium.myeonginshop.util.activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.app.Application
import android.os.Bundle
import com.iium.myeonginshop.util.common.CommonData
import com.iium.myeonginshop.util.preference.PreferenceManager

import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication : Application() {
    companion object {
        lateinit var prefs: PreferenceManager
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .name("test-data")
            .allowWritesOnUiThread(true)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
        prefs = PreferenceManager(applicationContext)
        val commonData: CommonData = CommonData().getInstance()
        commonData.numStarted = 0

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(p0: Activity, p1: Bundle?) {
            }

            override fun onActivityStarted(p0: Activity) {
                if (commonData.numStarted == 0) {
                    commonData.isMainRefresh = true
                    commonData.isForeground = true
                }
                commonData.numStarted++            }

            override fun onActivityResumed(p0: Activity) {
            }

            override fun onActivityPaused(p0: Activity) {
            }

            override fun onActivityStopped(p0: Activity) {
                commonData.numStarted--
                if (commonData.numStarted == 0) {
                    commonData.isForeground = false
                }            }

            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
            }

            override fun onActivityDestroyed(p0: Activity) {
            }
        })
    }
}