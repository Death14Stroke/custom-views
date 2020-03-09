package com.andruid.magic.customviews.ui.webview

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ProgressBar
import com.andruid.magic.eezetensions.hide
import com.andruid.magic.eezetensions.show

/**
 * Custom webChromeClient to show a progressBar while page is loading
 * @property progressBar progressBar in the parent view
 */
class ProgressWebChromeClient(private val progressBar: ProgressBar) : WebChromeClient() {
    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
        progressBar.progress = newProgress
        if (newProgress == 100)
            progressBar.hide()
        else
            progressBar.show()
    }
}