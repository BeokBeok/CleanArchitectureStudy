package com.beok.gitbeoktree.ext

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity

val AppCompatActivity.hasSourceBounds: Boolean get() = intent?.sourceBounds != null

fun AppCompatActivity.sourceBounds(boundsAction: (Rect) -> Unit) =
    intent?.sourceBounds?.let(boundsAction)


fun AppCompatActivity.preAnimationSetup() {
    if (hasSourceBounds) {
        overridePendingTransition(0, 0)
    }
}