package com.example.mcleancode.mobile.UI.Scanner

import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator

class ScannerShapeTransition(
        valueA: Float,
        valueB: Float ) {

    val animationDuration = 1000L
    var animator: ValueAnimator? = null

    init {
        animator = ValueAnimator.ofFloat(valueA, valueB)
        setupAnimatorWithDefaults(animator!!)
    }

    fun start() {
        animator!!.start()
    }

    private fun setupAnimatorWithDefaults(animator: ValueAnimator) {
        animator.duration = animationDuration
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE
        animator.interpolator = LinearInterpolator()
    }
}