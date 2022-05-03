package com.iium.myeonginshop.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.iium.myeonginshop.R
import com.iium.myeonginshop.databinding.ActivityMainBinding
import com.iium.myeonginshop.util.base.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this
        inStatusBar()
    }
}