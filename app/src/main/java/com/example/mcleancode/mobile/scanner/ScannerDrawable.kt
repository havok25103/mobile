package com.example.mcleancode.mobile.scanner

import android.animation.ValueAnimator
import android.graphics.*
import android.graphics.drawable.Drawable

class ScannerDrawable: Drawable() {
    private val glowColorStops = FloatArray(2)

    private val circlePaint = Paint()
    private val circleBlurPaint = Paint()
    private val donutPaint = Paint()

    private val darkBlueColor = Color.parseColor("#0f2535")
    private val darkBlueColorGlow = Color.TRANSPARENT
    private val brightBlueColor = Color.parseColor("#49bbfe")
    private val brightBlueColorGlow = Color.parseColor("#a0e1ff")
    private val brightRedColor = Color.parseColor("#fc4949")
    private val brightRedColorGlow = Color.parseColor("#ffa0a0")

    private var currentCircleRadius = 100f
    private var currentCircleBlurRadius = 130f
    private var currentDonutRadius = 170f
    private var currentDonutStrokeWidth = 40f
    private var currentColor = darkBlueColor
    private val currentGlowColors = IntArray(2)

    private val circleRadiusValueAnimator = ScannerShapeTransition(currentCircleRadius, 200f)
    private val circleBlurRadiusValueAnimator = ScannerShapeTransition(currentCircleBlurRadius, 250f)
    private val donutRadiusValueAnimator = ScannerShapeTransition(currentDonutRadius, 320f)
    private val donutStrokeWidthValueAnimator = ScannerShapeTransition(currentDonutStrokeWidth, 10f)
    private val farToNearColorAnimator = ScannerColorTransition(darkBlueColor, brightBlueColor, darkBlueColor, brightBlueColorGlow)
    private val nearToHereColorAnimator = ScannerColorTransition(brightBlueColor, brightRedColor, brightBlueColorGlow, brightRedColorGlow)
    private val hereToNearColorAnimator = ScannerColorTransition(brightRedColor, brightBlueColor, brightRedColorGlow, brightBlueColorGlow)
    private val nearToFarColorAnimator = ScannerColorTransition(brightBlueColor, darkBlueColor, brightBlueColorGlow, darkBlueColorGlow)

    init {
        currentGlowColors[0] = darkBlueColorGlow
        currentGlowColors[1] = Color.TRANSPARENT

        glowColorStops[0] = 0.8f
        glowColorStops[1] = 1.0f

        circleRadiusValueAnimator.animator!!.addUpdateListener { animation -> updateCircleRadius(animation) }
        circleBlurRadiusValueAnimator.animator!!.addUpdateListener { animation -> updateCirclBlureRadius(animation) }
        donutRadiusValueAnimator.animator!!.addUpdateListener { animation -> updateDonutRadius(animation) }
        donutStrokeWidthValueAnimator.animator!!.addUpdateListener { animation -> updateDonutStrokeWidth(animation) }
        farToNearColorAnimator.colorAnimator!!.addUpdateListener { animation -> updateColor(animation) }
        nearToHereColorAnimator.colorAnimator!!.addUpdateListener { animation -> updateColor(animation) }
        hereToNearColorAnimator.colorAnimator!!.addUpdateListener { animation -> updateColor(animation) }
        nearToFarColorAnimator.colorAnimator!!.addUpdateListener { animation -> updateColor(animation) }
        farToNearColorAnimator.glowAnimator!!.addUpdateListener { animation -> updateGlowColor(animation) }
        nearToHereColorAnimator.glowAnimator!!.addUpdateListener { animation -> updateGlowColor(animation) }
        hereToNearColorAnimator.glowAnimator!!.addUpdateListener { animation -> updateGlowColor(animation) }
        nearToFarColorAnimator.glowAnimator!!.addUpdateListener { animation -> updateGlowColor(animation) }

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
    }

    fun setNearToHere() {
        nearToHereColorAnimator.start()
    }

    fun setHereToNear() {
        hereToNearColorAnimator.start()
    }

    fun setNearToFar() {
        nearToFarColorAnimator.start()
    }

    private fun updateColor(animation: ValueAnimator) {
        currentColor = animation.animatedValue as Int
        invalidateSelf()
    }

    private fun updateGlowColor(animation: ValueAnimator) {
        currentGlowColors[0] = animation.animatedValue as Int
        invalidateSelf()
    }

    private fun updateCircleRadius(animation: ValueAnimator) {
        currentCircleRadius = animation.animatedValue as Float
        invalidateSelf()
    }

    private fun updateCirclBlureRadius(animation: ValueAnimator) {
        currentCircleBlurRadius = animation.animatedValue as Float
        invalidateSelf()
    }
    private fun updateDonutRadius(animation: ValueAnimator) {
        currentDonutRadius = animation.animatedValue as Float
        invalidateSelf()
    }

    private fun updateDonutStrokeWidth(animation: ValueAnimator) {
        currentDonutStrokeWidth = animation.animatedValue as Float
        invalidateSelf()
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
        canvas.drawCircle(centerX, centerY, currentCircleBlurRadius, circleBlurPaint)
        canvas.drawCircle(centerX, centerY, currentCircleRadius, circlePaint)
        canvas.drawCircle(centerX, centerY, currentDonutRadius, donutPaint)
    }
}