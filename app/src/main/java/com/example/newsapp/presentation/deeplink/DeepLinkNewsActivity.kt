package com.example.newsapp.presentation.deeplink

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.databinding.ActivityDeepLinkNewsBinding

class DeepLinkNewsActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityDeepLinkNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDeepLinkNewsBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        _binding.webView.settings.javaScriptEnabled = true
        val uri = intent.data
        if (uri != null) {
            _binding.webView.webViewClient = object : WebViewClient() {
                @Deprecated("Deprecated in Java")
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    url?.let { view?.loadUrl(it) }
                    return true
                }
            }
            _binding.webView.loadUrl(uri.toString())
        }
    }
}