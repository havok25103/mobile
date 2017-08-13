package com.example.mcleancode.mobile.drawable

import android.animation.ValueAnimator
import android.graphics.*
import android.graphics.drawable.Drawable
import android.view.animation.LinearInterpolator
import android.animation.ArgbEvaluator

class ScannerDrawable: Drawable() {
    private val animationDuration = 1000L

    private val glowColorStops = FloatArray(2)

    private val circlePaint = Paint()
    private val circleBlurPaint = Paint()
    private val donutPaint = Paint()

    private val blackColor = Color.parseColor("#111111")
    private val darkBlueColor = Color.parseColor("#0f2535")
    private val darkBlueColorGlow = Color.TRANSPARENT
    private val brightBlueColor = Color.parseColor("#49bbfe")
    private val brightBlueColorGlow = Color.parseColor("#a0e1ff")
    private val brightRedColor = Color.parseColor("#fc4949")
    private val brightRedColorGlow = Color.parseColor("#ffa0a0")

    private val circleRadiusValueAnimator: ValueAnimator = ValueAnimator.ofFloat(100f, 200f)

    private val circleBlurRadiusValueAnimator: ValueAnimator = ValueAnimator.ofFloat(130f, 250f)

    private val donutRadiusValueAnimator: ValueAnimator = ValueAnimator.ofFloat(170f, 320f)
    private val donutStrokeWidthValueAnimator: ValueAnimator = ValueAnimator.ofFloat(40f, 10f)

    private val farToNearColorAnimator: ValueAnimator = ValueAnimator.ofInt(darkBlueColor, brightBlueColor)
    private val farToNearGlowColorAnimator: ValueAnimator = ValueAnimator.ofInt(darkBlueColor, brightBlueColorGlow)

    private val nearToHereColorAnimator: ValueAnimator = ValueAnimator.ofInt(brightBlueColor, brightRedColor)
    private val nearToHereGlowColorAnimator: ValueAnimator = ValueAnimator.ofInt(brightBlueColorGlow, brightRedColorGlow)

    private val hereToNearColorAnimator: ValueAnimator = ValueAnimator.ofInt(brightRedColor, brightBlueColor)
    private val hereToNearGlowColorAnimator: ValueAnimator = ValueAnimator.ofInt(brightRedColorGlow, brightBlueColorGlow)

    private val nearToFarColorAnimator: ValueAnimator = ValueAnimator.ofInt(brightBlueColor, darkBlueColor)
    private val nearToFarGlowColorAnimator: ValueAnimator = ValueAnimator.ofInt(brightBlueColorGlow, darkBlueColorGlow)

    private var currentCircleRadius = 100f
    private var currentCircleBlurRadius = 130f
    private var currentDonutRadius = 170f
    private var currentDonutStrokeWidth = 40f
    private var currentColor = darkBlueColor
    private val currentGlowColors = IntArray(2)

    init {
        currentGlowColors[0] = darkBlueColorGlow
        currentGlowColors[1] = Color.TRANSPARENT

        glowColorStops[0] = 0.8f
        glowColorStops[1] = 1.0f

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

        setupColorAnimatorWithDefaults(farToNearColorAnimator) { animation ->
            currentColor = animation.animatedValue as Int
            invalidateSelf()
        }

        setupColorAnimatorWithDefaults(farToNearGlowColorAnimator) { animation ->
            currentGlowColors[0] = animation.animatedValue as Int
            invalidateSelf()
        }

        setupColorAnimatorWithDefaults(nearToHereColorAnimator) { animation ->
            currentColor = animation.animatedValue as Int
            invalidateSelf()
        }

        setupColorAnimatorWithDefaults(nearToHereGlowColorAnimator) { animation ->
            currentGlowColors[0] = animation.animatedValue as Int
            invalidateSelf()
        }

        setupColorAnimatorWithDefaults(hereToNearColorAnimator) { animation ->
            currentColor = animation.animatedValue as Int
            invalidateSelf()
        }

        setupColorAnimatorWithDefaults(hereToNearGlowColorAnimator) { animation ->
            currentGlowColors[0] = animation.animatedValue as Int
            invalidateSelf()
        }

        setupColorAnimatorWithDefaults(nearToFarColorAnimator) { animation ->
            currentColor = animation.animatedValue as Int
            invalidateSelf()
        }

        setupColorAnimatorWithDefaults(nearToFarGlowColorAnimator) { animation ->
            currentGlowColors[0] = animation.animatedValue as Int
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

        updateStyles(centerX, centerY)
        updateDrawing(canvas, centerX, centerY)
    }

    fun setFarToNear() {
        farToNearColorAnimator.start()
        farToNearGlowColorAnimator.start()
    }

    fun setNearToHere() {
        nearToHereColorAnimator.start()
        nearToHereGlowColorAnimator.start()
    }

    fun setHereToNear() {
        hereToNearColorAnimator.start()
        hereToNearGlowColorAnimator.start()
    }

    fun setNearToFar() {
        nearToFarColorAnimator.start()
        nearToFarGlowColorAnimator.start()
    }

    private fun setupAnimatorWithDefaults(animator: ValueAnimator, callback: (animation: ValueAnimator) -> Unit ) {
        animator.duration = animationDuration
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener { animation -> callback(animation) }
    }

    private fun setupColorAnimatorWithDefaults(animator: ValueAnimator, callback: (animation: ValueAnimator) -> Unit ) {
        animator.duration = animationDuration
        animator.interpolator = LinearInterpolator()
        animator.setEvaluator(ArgbEvaluator())
        animator.addUpdateListener { animation -> callback(animation) }
    }

    private fun centerX(): Float {
        return bounds.exactCenterX()
    }

    private fun centerY(): Float {
        return bounds.exactCenterY()
    }

    private fun updateStyles(centerX: Float, centerY: Float) {
        donutPaint.style = Paint.Style.STROKE
        donutPaint.strokeWidth = currentDonutStrokeWidth
        circlePaint.color = currentColor
        donutPaint.color = currentColor

        circleBlurPaint.shader = RadialGradient(
                centerX,
                centerY,
                currentCircleBlurRadius,
                currentGlowColors,
                glowColorStops,
                Shader.TileMode.CLAMP
        )
    }

    private fun updateDrawing(canvas: Canvas, centerX: Float, centerY: Float) {
        canvas.drawColor(blackColor)
        canvas.drawCircle(centerX, centerY, currentCircleBlurRadius, circleBlurPaint)
        canvas.drawCircle(centerX, centerY, currentCircleRadius, circlePaint)
        canvas.drawCircle(centerX, centerY, currentDonutRadius, donutPaint)
    }
}