package com.iium.myeonginshop.view.main

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.*
import androidx.databinding.DataBindingUtil
import com.iium.myeonginshop.R
import com.iium.myeonginshop.databinding.ActivityMainBinding
import com.iium.myeonginshop.util.base.BaseActivity
import com.iium.myeonginshop.util.log.LLog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.activity = this
        inStatusBar()
        initView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initView() {
        LLog.e("웹뷰 클라이언트")
        binding.webView.apply {
            webViewClient = WebViewClientClass()
            settings.javaScriptEnabled =  true
            settings.setSupportMultipleWindows(true)        //새창띄우기
            settings.javaScriptCanOpenWindowsAutomatically = true       //자바스크립트 새창띄우기
            settings.loadWithOverviewMode = true        //메타태크 허용
            settings.useWideViewPort = true             //화면 사이즈 맞추기 허용
            settings.setSupportZoom(true)               //화면 줌 허용
            settings.builtInZoomControls = true         //화면 확대 축소 허용

            settings.cacheMode =
                WebSettings.LOAD_NO_CACHE               //브라우저 캐시 허용
            settings.domStorageEnabled = true           //로컬저장소 허용
            settings.displayZoomControls = true

            settings.safeBrowsingEnabled = true
            settings.mediaPlaybackRequiresUserGesture = false

            settings.allowContentAccess = true
            settings.setGeolocationEnabled(true)
            settings.allowUniversalAccessFromFileURLs = true
            settings.allowFileAccess = true
            fitsSystemWindows = true
        }
        binding.webView.loadUrl("http://myunginshop.iiumns.com/")
    }

    inner class WebViewClientClass : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            url?.let {
                view?.loadUrl(it)
            }
            return true
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

        override fun onPageCommitVisible(view: WebView?, url: String?) {
            super.onPageCommitVisible(view, url)
        }

        @SuppressLint("WebViewClientOnReceivedSslError")
        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
            val builder : android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this@MainActivity)
            var message = "인증서 오류입니다."
            when (error?.primaryError) {
                SslError.SSL_UNTRUSTED -> message = "인증기관을 신뢰할 수 없습니다."
                SslError.SSL_EXPIRED -> message = "인증서가 만료되었습니다."
                SslError.SSL_IDMISMATCH -> message = "인증서 호스트가 일치하지 않습니다."
                SslError.SSL_NOTYETVALID -> message = "인증서가 유효하지 않습니다."
            }
            message += "계속 하시겠습니까?"
            builder.setTitle("인증서 오류")
            builder.setMessage(message)
            builder.setPositiveButton("계속") { _, i -> handler?.proceed() }
            builder.setNegativeButton("취소") { _, i -> handler?.cancel() }
            val dialog : android.app.AlertDialog? = builder.create()
            dialog?.show()
        }
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack())
        {
            binding.webView.goBack()
        }
        else
        {
            finish()
        }
    }
}