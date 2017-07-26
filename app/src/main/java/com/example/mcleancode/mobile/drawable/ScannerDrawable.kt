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

    val animationDuration = 1000L

    init {
        setupPaintsWithDefaults()

        setupAnimatorWithDefaults(circleRadiusValueAnimator) { animation ->
            currentCircleRadius = animation.animatedValue as Float
        }

        setupAnimatorWithDefaults(circleBlurRadiusValueAnimator) { animation ->
            currentCircleBlurRadius = animation.animatedValue as Float
        }

        setupAnimatorWithDefaults(donutRadiusValueAnimator) { animation ->
            currentDonutRadius = animation.animatedValue as Float
        }

        setupAnimatorWithDefaults(donutStrokeWidthValueAnimator) { animation ->
            currentDonutStrokeWidth = animation.animatedValue as Float
            invalidateSelf()
        }

        circleRadiusValueAnimator.start()
        circleBlurRadiusValueAnimator.start()
        donutRadiusValueAnimator.start()
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
        val centerX = centerX()
        val centerY = centerY()
        updateDrawing(canvas, centerX, centerY)
    }

    private fun setupPaintsWithDefaults() {
        circlePaint.color = brightBlueColor
        donutPaint.style = Paint.Style.STROKE
        donutPaint.color = brightBlueColor
    }

    private fun setupAnimatorWithDefaults(animator: ValueAnimator, callback: (animation: ValueAnimator) -> Unit ) {
        animator.duration = animationDuration
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener { animation -> callback(animation) }
    }

    private fun centerX(): Float {
        return bounds.exactCenterX()
    }

    private fun centerY(): Float {
        return bounds.exactCenterY()
    }

    private fun updateDrawing(canvas: Canvas, centerX: Float, centerY: Float) {
        donutPaint.strokeWidth = currentDonutStrokeWidth
        circleBlurPaint.shader = RadialGradient(centerX, centerY, currentCircleBlurRadius, brightBlueColor, Color.TRANSPARENT, Shader.TileMode.CLAMP)

        canvas.drawColor(blackColor)
        canvas.drawCircle(centerX, centerY, currentCircleRadius, circlePaint)
        canvas.drawCircle(centerX, centerY, currentCircleBlurRadius, circleBlurPaint)
        canvas.drawCircle(centerX, centerY, currentDonutRadius, donutPaint)
    }
}