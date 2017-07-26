package com.example.mcleancode.mobile.animation.scanner

import android.graphics.*

class ScannerCircleBlurAnimation(val color: Int, framesPerSecond: Int): ScannerComponent(framesPerSecond) {
    override val startRadius = 130f
    override val endRadius = 230f

    override var radius = 130f

    fun step(rate: Float, canvas: Canvas, x: Float, y: Float, glowing: Boolean) {
        setupPaint(x, y, glowing)
        scaleCircle(rate)
        draw(canvas, x, y)
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
