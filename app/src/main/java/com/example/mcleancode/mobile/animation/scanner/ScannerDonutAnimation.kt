package com.example.mcleancode.mobile.animation.scanner

import android.graphics.*

class ScannerDonutAnimation(val lightColor: Int, val darkColor: Int, framesPerSecond: Int): ScannerComponent(framesPerSecond) {
    override val startRadius = 170f
    override val endRadius = 320f

    override val startStrokeWidth = 40f
    override val endStrokeWidth = 10f

    override var radius = 170f
    override var strokeWidth = 40f

    fun step(rate: Float, canvas: Canvas, x: Float, y: Float, glowing: Boolean) {
        setupPaint(glowing)
        scaleCircle(rate)
        scaleStroke(rate)
        draw(canvas, x, y)
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
