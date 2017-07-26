package com.example.mcleancode.mobile.animation.scanner

import android.graphics.*

class ScannerCircleAnimation(val lightColor: Int, val darkColor: Int): ScannerComponent() {
    override val radiusMin = 100f
    override val radiusMax = 200f

    override var radius = 100f
    override var pulseDir = 1

    fun step(rate: Float, canvas: Canvas, x: Float, y: Float, glowing: Boolean) {
        setupPaint(glowing)
        draw(canvas, x, y)
        scaleCircle(rate = rate, delta = 2f)
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
