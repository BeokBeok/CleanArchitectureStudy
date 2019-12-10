package com.beok.gitbeoktree

import android.animation.AnimatorSet
import android.os.Bundle
import androidx.core.view.isInvisible
import com.beok.common.base.BaseActivity
import com.beok.common.ext.goActivity
import com.beok.gitbeoktree.databinding.ActivitySplashBinding
import com.beok.gitbeoktree.ext.*
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import kotlin.math.hypot

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        preAnimationSetup()
        super.onCreate(savedInstanceState)
        initAdMob()
        performCircularReveal()
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

                    override fun onAdFailedToLoad(errorCode: Int) {
                        goActivity(MainActivity::class.java)
                    }

                    override fun onAdClosed() {
                        goActivity(MainActivity::class.java)
                    }
                }
        }
        interstitialAd.loadAd(AdRequest.Builder().build())
    }

    private fun performCircularReveal() {
        if (!hasSourceBounds) {
            binding.clRootLayout.isInvisible = false
            return
        }

        sourceBounds { sourceBounds ->
            binding.clRootLayout.run {
                screenBounds { rootLayoutBounds ->
                    if (!rootLayoutBounds.contains(sourceBounds)) {
                        isInvisible = false
                        return@screenBounds
                    }

                    val circle = createCircularReveal(
                        centerX = sourceBounds.centerX() - rootLayoutBounds.left,
                        centerY = sourceBounds.centerY() - rootLayoutBounds.top,
                        startRadius = (minOf(sourceBounds.width(), sourceBounds.height()) * 0.2)
                            .toFloat(),
                        endRadius = hypot(width.toFloat(), height.toFloat())
                    ).apply {
                        isInvisible = false
                        duration = 1000L
                    }
                    AnimatorSet().apply { playTogether(circle) }
                        .start()
                }
            }

        }
    }
}
