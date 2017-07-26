package com.example.mcleancode.mobile.animation.scanner

import android.graphics.*

class ScannerDonutAnimation(val lightColor: Int, val darkColor: Int): ScannerComponent() {
    override val radiusMin = 170f
    override val radiusMax = 320f

    override var radius = 170f
    override var pulseDir = 1
    override var strokeWidth = 40f

    fun step(rate: Float, canvas: Canvas, x: Float, y: Float, glowing: Boolean) {
        setupPaint(glowing)
        draw(canvas, x, y)
        scaleCircle(rate = rate, delta = 3f)
        scaleStroke(rate = rate, delta = -0.25f)
        handleDirectionFlip()
    }

    private fun setupPaint(glowing: Boolean) {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth

        if(glowing) {
            paint.color = lightColor
        } else {
            paint.color = darkColor
        }
    }
}
