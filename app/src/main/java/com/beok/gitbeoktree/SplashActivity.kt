package com.beok.gitbeoktree

import android.animation.AnimatorSet
import android.os.Bundle
import androidx.core.view.isInvisible
import com.beok.common.base.BaseActivity
import com.beok.common.ext.goActivity
import com.beok.gitbeoktree.databinding.ActivitySplashBinding
import com.beok.gitbeoktree.ext.createCircularReveal
import com.beok.gitbeoktree.ext.screenBounds
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

                    override fun onAdClosed() {
                        goActivity(MainActivity::class.java)
                    }
                }
        }
        interstitialAd.loadAd(AdRequest.Builder().build())
    }

    private fun hasSourceBounds(): Boolean = intent?.sourceBounds != null

    private fun preAnimationSetup() {
        if (hasSourceBounds()) overridePendingTransition(0, 0)
    }

    private fun performCircularReveal() {
        if (!hasSourceBounds()) {
            binding.clRootLayout.isInvisible = false
            return
        }
        intent?.sourceBounds?.let { sourceBounds ->
            binding.clRootLayout.run {
                screenBounds { rootLayoutBounds ->
                    if (rootLayoutBounds.contains(sourceBounds)) {
                        isInvisible = false
                        return@screenBounds
                    }

                    val circle = createCircularReveal(
                        centerX = sourceBounds.centerX() - rootLayoutBounds.left,
                        centerY = sourceBounds.centerY() - rootLayoutBounds.top,
                        startRadius = (minOf(
                            sourceBounds.width(),
                            sourceBounds.height()
                        ) * 0.2).toFloat(),
                        endRadius = hypot(width.toFloat(), height.toFloat())
                    ).apply {
                        isInvisible = false
                        duration = 500L
                    }
                    AnimatorSet().apply { playTogether(circle) }
                        .start()
                }
            }

        }
    }
}
