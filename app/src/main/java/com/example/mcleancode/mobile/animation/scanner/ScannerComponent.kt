package com.example.mcleancode.mobile.animation.scanner

import android.graphics.Canvas
import android.graphics.Paint

open class ScannerComponent(val framesPerSecond: Int) {
    open protected val paint = Paint()
    open protected val startRadius = 0f
    open protected val endRadius = 0f
    open protected val startStrokeWidth = 0f
    open protected val endStrokeWidth = 0f

    open protected var radius = 0f
    open protected var strokeWidth = 0f
    open protected var pulseDir = 1

    protected fun draw(canvas: Canvas, x: Float, y: Float) {
        canvas.drawCircle(x, y, radius, paint)
    }

    protected fun handleDirectionFlip() {
        val radiusList = arrayOf(startRadius, endRadius)

        if(radius >= radiusList.max()!! || radius <= radiusList.min()!!) {
            pulseDir *= -1
        }
    }

    protected fun scaleCircle(rate: Float) {
        radius += pulseDir * rate *  delta(startRadius, endRadius)
    }

    protected fun scaleStroke(rate: Float) {
        strokeWidth += pulseDir * rate * delta(startStrokeWidth, endStrokeWidth)
    }

    protected fun delta(min: Float, max: Float): Float {
        return ( max - min ) / framesPerSecond
    }
}