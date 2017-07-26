package com.example.mcleancode.mobile.view

import android.content.Context
import android.graphics.*
import android.view.View
import com.example.mcleancode.mobile.animation.scanner.ScannerCircleAnimation
import com.example.mcleancode.mobile.animation.scanner.ScannerCircleBlurAnimation
import com.example.mcleancode.mobile.animation.scanner.ScannerDonutAnimation

class ScannerView(context: Context): View(context) {
    var animationSpeed = 1.0f
    var proximity = 0

    private val darkBlueColor = Color.parseColor("#0f2535")
    private val brightBlueColor = Color.parseColor("#49bbfe")
    private val blackColor = Color.parseColor("#111111")

    private val donutAnimation = ScannerDonutAnimation(brightBlueColor, darkBlueColor)
    private val circleAnimation = ScannerCircleAnimation(brightBlueColor, darkBlueColor)
    private val circleBlurAnimation = ScannerCircleBlurAnimation(brightBlueColor)

    init {
        this.postInvalidate()
    }

    override fun onDraw(canvas: Canvas) {
        drawScene(canvas)
        loopAnimation()
    }

    private fun drawScene(canvas: Canvas) {
        val centerX = width / 2f
        val centerY = height / 2f
        val glowing = proximity > 0

        canvas.drawColor(blackColor)

        circleBlurAnimation.step(
                rate = animationSpeed,
                canvas = canvas,
                x = centerX,
                y = centerY,
                glowing = glowing
        )

        circleAnimation.step(
                rate = animationSpeed,
                canvas = canvas,
                x = centerX,
                y = centerY,
                glowing = glowing
        )

        donutAnimation.step(
                rate = animationSpeed,
                canvas = canvas,
                x = centerX,
                y = centerY,
                glowing = glowing
        )
    }

    private fun loopAnimation() {
        this.postInvalidateDelayed((1000 / 60).toLong())
    }
}