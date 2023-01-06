package com.ad8.presentation.util

import android.graphics.*
import android.graphics.drawable.Drawable

class ColorCircleDrawable(color: Int) : Drawable() {
    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mRadius = 0
    override fun draw(canvas: Canvas) {
        val bounds = bounds
        canvas.drawCircle(
            bounds.centerX().toFloat(),
            bounds.centerY().toFloat(),
            mRadius.toFloat(),
            mPaint
        )
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        mRadius = Math.min(bounds.width(), bounds.height()) / 2
    }

    override fun setAlpha(alpha: Int) {
        mPaint.alpha = alpha
    }

    override fun setColorFilter(cf: ColorFilter?) {
        mPaint.colorFilter = cf
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    init {
        mPaint.color = color
    }
}