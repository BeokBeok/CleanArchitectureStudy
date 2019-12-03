package com.beok.gitbeoktree.ext

import android.animation.Animator
import android.graphics.Rect
import android.view.View
import android.view.ViewAnimationUtils
import androidx.core.view.ViewCompat
import androidx.core.view.doOnNextLayout

private fun View.computeScreenBounds(): Rect {
    val viewLocation = IntArray(2).apply { getLocationOnScreen(this) }
    val contentX = viewLocation[0]
    val contentY = viewLocation[1]
    return Rect(
        contentX,
        contentY,
        contentX + width,
        contentY + height
    )
}

fun View.screenBounds(action: (Rect) -> Unit) {
    if (!ViewCompat.isLaidOut(this) && !isLayoutRequested) {
        action(computeScreenBounds())
    } else {
        doOnNextLayout {
            action(computeScreenBounds())
        }
    }
}

fun View.createCircularReveal(
    centerX: Int,
    centerY: Int,
    startRadius: Float,
    endRadius: Float
): Animator {
    return ViewAnimationUtils.createCircularReveal(
        this,
        centerX,
        centerY,
        startRadius,
        endRadius
    )
}