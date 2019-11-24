package com.beok.gitbeoktree

import android.os.Bundle
import com.beok.common.base.BaseActivity
import com.beok.gitbeoktree.databinding.ActivityMainBinding
import com.google.android.gms.ads.MobileAds

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this, getString(R.string.app_key_admob))
    }
}
