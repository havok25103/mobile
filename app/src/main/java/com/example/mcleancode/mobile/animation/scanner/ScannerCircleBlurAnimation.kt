package com.example.mcleancode.mobile.animation.scanner

import android.graphics.*

class ScannerCircleBlurAnimation(val color: Int): ScannerComponent() {
    override val radiusMin = 130f
    override val radiusMax = 230f

    override var radius = 130f
    override var pulseDir = 1

    fun step(rate: Float, canvas: Canvas, x: Float, y: Float, glowing: Boolean) {
        setupPaint(x, y, glowing)
        draw(canvas, x, y)
        scaleCircle(rate = rate, delta = 2f)
        handleDirectionFlip()
    }

    private fun setupPaint(x: Float, y: Float, glowing: Boolean) {
        if(glowing) {
            paint.shader = RadialGradient(x, y, radius, color, Color.TRANSPARENT, Shader.TileMode.CLAMP)
        } else {
            paint.shader = RadialGradient(x, y, radius, Color.TRANSPARENT, Color.TRANSPARENT, Shader.TileMode.CLAMP)
        }
    }
}
