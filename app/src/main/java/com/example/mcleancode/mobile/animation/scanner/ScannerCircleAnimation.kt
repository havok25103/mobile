package com.example.mcleancode.mobile.animation.scanner

import android.graphics.*

class ScannerCircleAnimation(val lightColor: Int, val darkColor: Int, framesPerSecond: Int): ScannerComponent(framesPerSecond) {
    override val startRadius = 100f
    override val endRadius = 200f

    override var radius = 100f

    fun step(rate: Float, canvas: Canvas, x: Float, y: Float, glowing: Boolean) {
        setupPaint(glowing)
        scaleCircle(rate)
        draw(canvas, x, y)
        handleDirectionFlip()
    }

    private fun setupPaint(glowing: Boolean) {
        if(glowing) {
            paint.color = lightColor
        } else {
            paint.color = darkColor
        }
    }
}
