package com.example.mcleancode.mobile.view

import android.content.Context
import android.graphics.*
import android.view.View

class ScannerView(context: Context): View(context) {
    var animationSpeed = 1.0f
    var proximity = 0

    private var circleBlurRadius = 130f
    private val circleBlurRadiusMin = 130f
    private val circleBlurRadiusMax = 230f
    private var circleBlurPulseDir = 1

    private var donutRadius = 170f
    private var donutStrokeWidth = 40f
    private val donutRadiusMin = 170f
    private val donutRadiusMax = 320f
    private var donutPulseDir = 1

    private var circleRadius = 100f
    private val circleRadiusMin = 100f
    private val circleRadiusMax = 200f
    private var circlePulseDir = 1

    private val circlePaint = Paint()
    private val donutPaint = Paint()
    private val circleBlurPaint = Paint()

    private val darkBlueColor = Color.parseColor("#0f2535")
    private val brightBlueColor = Color.parseColor("#49bbfe")
    private val blackColor = Color.parseColor("#111111")

    init {
        this.postInvalidate()
    }

    override fun onDraw(canvas: Canvas) {
        drawScene(canvas)
        scaleCircle()
        scaleCircleBlur()
        scaleDonut()
        loopAnimation()
    }

    private fun drawScene(canvas: Canvas) {
        val centerX = width / 2f
        val centerY = height / 2f

        donutPaint.style = Paint.Style.STROKE
        donutPaint.strokeWidth = donutStrokeWidth

        if(proximity == 0) {
            circlePaint.color = darkBlueColor
            donutPaint.color = darkBlueColor
            circleBlurPaint.shader = RadialGradient(centerX, centerY, circleBlurRadius, Color.TRANSPARENT, Color.TRANSPARENT, Shader.TileMode.CLAMP)
        } else {
            circlePaint.color = brightBlueColor
            donutPaint.color = brightBlueColor
            circleBlurPaint.shader = RadialGradient(centerX, centerY, circleBlurRadius, brightBlueColor, Color.TRANSPARENT, Shader.TileMode.CLAMP)
        }

        canvas.drawColor(blackColor)
        canvas.drawCircle(centerX, centerY, circleBlurRadius, circleBlurPaint)
        canvas.drawCircle(centerX, centerY, circleRadius, circlePaint)
        canvas.drawCircle(centerX, centerY, donutRadius, donutPaint)
    }

    private fun scaleCircle() {
        val radiusIncrease = 2
        circleRadius += circlePulseDir * animationSpeed * radiusIncrease
    }

    private fun scaleCircleBlur() {
        val radiusIncrease = 2
        circleBlurRadius += circleBlurPulseDir * animationSpeed * radiusIncrease
    }

    private fun scaleDonut() {
        val radiusIncrease = 3
        val strokeWidthDecrease = -0.25f
        donutRadius += donutPulseDir * animationSpeed * radiusIncrease
        donutStrokeWidth += donutPulseDir * animationSpeed * strokeWidthDecrease
    }

    private fun loopAnimation() {
        if(circleRadius >= circleRadiusMax || circleRadius <= circleRadiusMin) {
            circlePulseDir *= -1
        }

        if(circleBlurRadius >= circleBlurRadiusMax || circleBlurRadius <= circleBlurRadiusMin) {
            circleBlurPulseDir *= -1
        }

        if(donutRadius >= donutRadiusMax || donutRadius <= donutRadiusMin) {
            donutPulseDir *= -1
        }

        this.postInvalidateDelayed((1000 / 60).toLong())
    }
}