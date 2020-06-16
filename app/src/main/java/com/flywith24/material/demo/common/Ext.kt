package com.flywith24.material.demo.common

import android.content.res.Resources
import android.util.TypedValue
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

/**
 * @author Flywith24
 * @date   2020/6/16
 * time   10:14
 * description
 */

fun TextView.setTopDrawable(@DrawableRes drawableResId: Int) {
    compoundDrawablePadding = 20f.toPX()
    setCompoundDrawablesWithIntrinsicBounds(
        null,
        ContextCompat.getDrawable(context, drawableResId),
        null,
        null
    )
}

fun Float.toPX(): Int =
    TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    ).toInt()
