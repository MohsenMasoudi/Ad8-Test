package com.ad8.presentation.util

import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.res.TypedArray
import com.ad8.presentation.R
import android.view.animation.AccelerateDecelerateInterpolator
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import kotlin.math.roundToInt

class CountingTextView : AppCompatTextView {
    /**
     * Gets the startValue
     */
    /**
     * Sets the startValue
     */
    var startValue = 0
    /**
     * Gets the endValue
     */
    /**
     * Sets the endValue
     *
     * @param endValue value animate to
     */
    var endValue = 0
    /**
     * Gets the duration
     *
     * @return animation duration
     */
    /**
     * Sets the duration
     *
     * @param duration animation duration
     */
    var duration = 1200
    /**
     * Gets the format, default is %s
     *
     * @return current format
     */
    /**
     * Sets the string format of your choice See: http://developer.android.com/reference/java/util/Formatter.html
     *
     * @param format fromat to text displaying
     */
    var format = "%d"
    /**
     * Gets the interpolator
     *
     * @return current time interpolator
     */
    /**
     * Sets the interpolator See: http://developer.android.com/reference/android/view/animation/Interpolator.html
     *
     * @param interpolator set interpolator for counting animation
     */
    var interpolator: TimeInterpolator? = null
    private var animator: ValueAnimator? = null

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    ) {
        if (isInEditMode) {
            text = text
        }
        init(attrs, defStyle)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        if (isInEditMode) {
            text = text
        }
        init(attrs, 0)
    }

    constructor(context: Context?) : this(context, null) {
        init(null, 0)
    }

    /**
     * Initial method that sets default duration and default interpolator
     */
    @SuppressLint("CustomViewStyleable")
    private fun init(attrs: AttributeSet?, defStyle: Int) {
        var a: TypedArray? = null
        try {
            a = context.obtainStyledAttributes(
                attrs, R.styleable.countingTextView, defStyle, 0
            )
            duration = a.getInt(
                R.styleable.countingTextView_duration, 1200
            )
        } finally {
            a?.recycle()
        }
        interpolator = AccelerateDecelerateInterpolator()
    }

    /**
     * Animates from zero to current endValue, with duration
     *
     * @param duration animation duration
     */
    fun animateFromZerotoCurrentValue(duration: Int) {
        this.duration = duration
        animateText(0, endValue)
    }

    /**
     * Animates from zero to current endValue
     */
    fun animateFromZerotoCurrentValue() {
        animateText(0, endValue)
    }

    /**
     * Animates from zero to given endValue with given duration
     *
     * @param endValue value animate to
     * @param duration value animate to
     */
    fun animateFromZero(endValue: Int, duration: Int) {
        this.duration = duration
        animateText(0, endValue)
    }

    /**
     * Animates from zero to given endValue
     *
     * @param endValue value animate to
     */
    fun animateFromZero(endValue: Int) {
        animateText(0, endValue)
    }

    /**
     * Animates from startValue to endValue with given duration
     *
     * @param duration value animate to
     */

    fun animateTextWithDuration(duration: Int) {
        this.duration = duration
        animateText(startValue, endValue)
    }
    /**
     * Actual method that plays the animation with given values
     *
     * @param startValue value animate from
     * @param endValue   value animate to
     */
    /**
     * Animates from startValue to endValue
     */
    @JvmOverloads
    fun animateText(startValue: Int = this.startValue, endValue: Int = this.endValue) {
        this.startValue = startValue
        this.endValue = endValue
        val animator = ValueAnimator.ofInt(startValue, endValue)
        animator.interpolator = interpolator
        animator.addUpdateListener { animation ->
            text = String.format(format, animation.animatedValue)
        }
        animator.setEvaluator { fraction, startValue, endValue -> ((startValue as Int) + ((endValue as Int) - (startValue)) * fraction).roundToInt() }
        animator.duration = duration.toLong()
        if (this.animator != null) {
            this.animator!!.cancel()
        }
        this.animator = animator
        animator.start()
    }
}