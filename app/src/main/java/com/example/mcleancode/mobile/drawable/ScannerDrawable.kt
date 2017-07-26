package com.example.mcleancode.mobile.drawable

import android.animation.ValueAnimator
import android.graphics.*
import android.graphics.drawable.Drawable
import android.view.animation.LinearInterpolator

class ScannerDrawable: Drawable() {

    private val darkBlueColor = Color.parseColor("#0f2535")
    private val brightBlueColor = Color.parseColor("#49bbfe")
    private val blackColor = Color.parseColor("#111111")

    val circleRadiusValueAnimator: ValueAnimator = ValueAnimator.ofFloat(100f, 200f)
    val circleBlurRadiusValueAnimator: ValueAnimator = ValueAnimator.ofFloat(130f, 230f)
    val donutRadiusValueAnimator: ValueAnimator = ValueAnimator.ofFloat(170f, 320f)
    val donutStrokeWidthValueAnimator: ValueAnimator = ValueAnimator.ofFloat(40f, 10f)

    var currentCircleRadius = 100f
    var currentCircleBlurRadius = 130f
    var currentDonutRadius = 170f
    var currentDonutStrokeWidth = 40f

    val circlePaint = Paint()
    val circleBlurPaint = Paint()
    val donutPaint = Paint()

    init {
        circlePaint.color = brightBlueColor

        donutPaint.style = Paint.Style.STROKE
        donutPaint.color = brightBlueColor

        circleRadiusValueAnimator.duration = 1000
        circleRadiusValueAnimator.repeatCount = ValueAnimator.INFINITE
        circleRadiusValueAnimator.repeatMode = ValueAnimator.REVERSE
        circleRadiusValueAnimator.interpolator = LinearInterpolator()
        circleRadiusValueAnimator.addUpdateListener { animation ->
            currentCircleRadius = animation.animatedValue as Float
        }
        circleRadiusValueAnimator.start()

        circleBlurRadiusValueAnimator.duration = 1000
        circleBlurRadiusValueAnimator.repeatCount = ValueAnimator.INFINITE
        circleBlurRadiusValueAnimator.repeatMode = ValueAnimator.REVERSE
        circleBlurRadiusValueAnimator.interpolator = LinearInterpolator()
        circleBlurRadiusValueAnimator.addUpdateListener { animation ->
            currentCircleBlurRadius = animation.animatedValue as Float
        }
        circleBlurRadiusValueAnimator.start()

        donutRadiusValueAnimator.duration = 1000
        donutRadiusValueAnimator.repeatCount = ValueAnimator.INFINITE
        donutRadiusValueAnimator.repeatMode = ValueAnimator.REVERSE
        donutRadiusValueAnimator.interpolator = LinearInterpolator()
        donutRadiusValueAnimator.addUpdateListener { animation ->
            currentDonutRadius = animation.animatedValue as Float
        }
        donutRadiusValueAnimator.start()

        donutStrokeWidthValueAnimator.duration = 1000
        donutStrokeWidthValueAnimator.repeatCount = ValueAnimator.INFINITE
        donutStrokeWidthValueAnimator.repeatMode = ValueAnimator.REVERSE
        donutStrokeWidthValueAnimator.interpolator = LinearInterpolator()
        donutStrokeWidthValueAnimator.addUpdateListener { animation ->
            currentDonutStrokeWidth = animation.animatedValue as Float
            invalidateSelf()
        }
        donutStrokeWidthValueAnimator.start()
    }

    override fun getOpacity(): Int {
        return PixelFormat.OPAQUE
    }

    override fun setAlpha(alpha: Int) {
        TODO("not implemented")
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        TODO("not implemented")
    }

    override fun draw(canvas: Canvas?) {
        if(canvas == null) { return }

        val bounds = bounds
        val centerX = bounds.exactCenterX()
        val centerY = bounds.exactCenterY()

        circleBlurPaint.shader = RadialGradient(centerX, centerY, currentCircleBlurRadius, brightBlueColor, Color.TRANSPARENT, Shader.TileMode.CLAMP)
        donutPaint.strokeWidth = currentDonutStrokeWidth

        canvas.drawColor(blackColor)
        canvas.drawCircle(centerX, centerY, currentCircleRadius, circlePaint)
        canvas.drawCircle(centerX, centerY, currentCircleBlurRadius, circleBlurPaint)
        canvas.drawCircle(centerX, centerY, currentDonutRadius, donutPaint)
    }
}