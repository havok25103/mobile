package com.example.mcleancode.mobile.scanner

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.view.animation.LinearInterpolator

class ScannerColorTransition(
        colorA: Int,
        colorB: Int,
        glowA: Int,
        glowB: Int ) {

    val animationDuration = 1000L

    var colorAnimator: ValueAnimator? = null
    var glowAnimator: ValueAnimator? = null

    init {
        colorAnimator = ValueAnimator.ofInt(colorA, colorB)
        glowAnimator = ValueAnimator.ofInt(glowA, glowB)

        setupAnimatorWithDefaults(colorAnimator!!)
        setupAnimatorWithDefaults(glowAnimator!!)
    }

    fun start() {
        colorAnimator!!.start()
        glowAnimator!!.start()
    }

    private fun setupAnimatorWithDefaults(animator: ValueAnimator) {
        animator.duration = animationDuration
        animator.interpolator = LinearInterpolator()
        animator.setEvaluator(ArgbEvaluator())
    }
}