package com.wpt.yhcc

import android.net.http.SslError
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.*

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initWeb()

    }

    private fun initWeb(){

        webView = findViewById(R.id.webView)
        val url = "https://c2099.shopeex.cn/v3/#/"
        val setting: WebSettings = webView.getSettings()
        setting.domStorageEnabled = true
        setting.javaScriptEnabled = true
        setting.setSupportZoom(true)
        setting.builtInZoomControls = true
        setting.displayZoomControls = false
        setting.useWideViewPort = true
        setting.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        setting.loadWithOverviewMode = true
        webView.clearCache(true)
        webView.webViewClient = object : WebViewClient(){
            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                super.onReceivedSslError(view, handler, error)
                handler?.proceed()
            }
        }
        webView.loadUrl(url)

    }
}