package com.iium.myeonginshop.util.preference

import android.content.Context
import android.content.SharedPreferences
import com.iium.myeonginshop.util.`object`.Constant
import com.securepreferences.SecurePreferences

class PreferenceManager (context: Context) : PreferenceAdapter() {
    private val PREFS_FILENAME : String = "prefs"
    private val prefs = context.getSharedPreferences(PREFS_FILENAME, 0)    // 0 = MODE_PRIVATE
    private var share : SharedPreferences
    private val shareEditor : SharedPreferences.Editor
    private var securePreferences: SecurePreferences? = null
    private var securePrefEditor: SecurePreferences.Editor? = null

    private fun PreferenceManager(context: Context) {
        val sharedPref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
        securePreferences = SecurePreferences(context)
        securePrefEditor = securePreferences!!.edit()
        super.setPreference(sharedPref)
    }

    private fun getString(key: String) : String? {
        return getString(key,"").apply {  }
    }

    internal fun getStr(key: String?, s: String): String? {
        return getString(key.toString())
    }

    internal fun setStr(key: String?, value: String?): Boolean {
        return setString(key, value)
    }

    fun setSecureString(key: String?, value: String?): Boolean {
        shareEditor.putString(key, value)
        return shareEditor.commit()
    }

    fun getSecureString(key: String?): String? {
        return share.getString(key, "")
    }

    fun getBool(key: String?): Boolean {
        return getBoolean(key, false)
    }

    fun setBool(key: String?, value: Boolean): Boolean {
        return setBoolean(key, value)
    }

    companion object {
        private var instance: PreferenceManager? = null
        @JvmStatic
        fun getInstance(context: Context): PreferenceManager? {
            if (null == instance) {
                synchronized(PreferenceManager::class.java) {
                    if (null == instance) {
                        instance = PreferenceManager(context)
                    }
                }
            }
            return instance
        }
    }
    init {
        val sharedPref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context)
        share = SecurePreferences(context)
        shareEditor = share.edit()
        super.setPreference(sharedPref)
    }
}