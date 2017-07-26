package com.example.mcleancode.mobile.animation.scanner

import android.graphics.Canvas
import android.graphics.Paint

open class ScannerComponent {
    protected val paint = Paint()

    open protected var radius = 0f
    open protected var strokeWidth = 0f
    open protected var pulseDir = 0
    open protected val radiusMax = 0f
    open protected val radiusMin = 0f

    protected fun draw(canvas: Canvas, x: Float, y: Float) {
        canvas.drawCircle(x, y, radius, paint)
    }

    protected fun handleDirectionFlip() {
        if(radius >= radiusMax || radius <= radiusMin) {
            pulseDir *= -1
        }
    }

    protected fun scaleCircle(rate: Float, delta: Float) {
        radius += pulseDir * rate * delta
    }

    protected fun scaleStroke(rate: Float, delta: Float) {
        strokeWidth += pulseDir * rate * delta
    }
}