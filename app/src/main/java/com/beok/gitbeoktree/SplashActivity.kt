package com.beok.gitbeoktree

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.beok.common.ext.goActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAdMob()
    }

    private fun initAdMob() {
        MobileAds.initialize(this, getString(R.string.app_key_admob))
        val interstitialAd = InterstitialAd(this).apply {
            adUnitId =
                if (BuildConfig.DEBUG) getString(R.string.app_test_key_interstitial)
                else getString(R.string.app_key_interstitial)
            adListener =
                object : AdListener() {
                    override fun onAdLoaded() {
                        show()
                    }

                    override fun onAdClosed() {
                        goMainActivity()
                    }
                }
        }
        interstitialAd.loadAd(AdRequest.Builder().build())
    }

    private fun goMainActivity() {
        goActivity(MainActivity::class.java)
        finish()
    }
}
