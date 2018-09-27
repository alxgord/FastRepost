package com.artto.fastrepost.ui.element

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet

class ResizableViewPager(context: Context, attributes: AttributeSet? = null) : ViewPager(context, attributes) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var height = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED))
            val childHeight = child.measuredHeight
            if (childHeight > height) height = childHeight
        }

        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY))
    }
}