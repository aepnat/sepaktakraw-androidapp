package com.saepulbahri.sepaktakrawindustry

import android.annotation.SuppressLint
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val url_website = "http://sepaktakrawindustry.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        settings();
        load_website();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun settings(){
        val websettings = webview.settings
        websettings.javaScriptEnabled = true
        websettings.domStorageEnabled = true
    }

    private fun load_website() {
        if(Build.VERSION.SDK_INT >= 19){
            webview.setLayerType(View.LAYER_TYPE_HARDWARE, null)
        }else{
            webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        }
        webview.webChromeClient = object: WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                progressContain.visibility = View.VISIBLE
                loading.visibility = View.VISIBLE
                loading.progress = newProgress
                if(newProgress == 100){
                    progressContain.visibility = View.GONE
                    loading.visibility = View.GONE
                }
                super.onProgressChanged(view, newProgress)
            }
        }

        webview.webViewClient = object: WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, URL: String?): Boolean {
                view?.loadUrl(URL)
                progressContain.visibility = View.VISIBLE
                loading.visibility = View.VISIBLE
                return true
            }
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view?.loadUrl(request?.url.toString())
                }
                progressContain.visibility = View.VISIBLE
                loading.visibility = View.VISIBLE
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progressContain.visibility = View.GONE
                loading.visibility = View.GONE
            }
        }

        webview.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webview.loadUrl(url_website)
    }

    override fun onBackPressed() {
        if(webview.canGoBack()) {
            webview.goBack()
        }else{
            super.onBackPressed()
        }
    }

}
