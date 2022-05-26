package com.frood.app.presentation.util.extension

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment


fun Context.colorOf(@ColorRes colorRes: Int): Int {
    return ResourcesCompat.getColor(resources, colorRes, theme)
}

fun Context.colorAttrOf(@AttrRes attrRes: Int): Int {
    val value = TypedValue()
    theme.resolveAttribute(attrRes, value, true)
    return value.data
}

fun Context.colorStateListOf(@ColorRes colorRes: Int): ColorStateList {
    return ColorStateList.valueOf(ResourcesCompat.getColor(resources, colorRes, theme))
}

fun Context.colorStateListFrom(color: Int): ColorStateList {
    return ColorStateList.valueOf(color)
}

fun Context.colorStateListFromAttrOf(@AttrRes attrRes: Int): ColorStateList {
    return colorStateListFrom(colorAttrOf(attrRes))
}

fun Context.drawableOf(@DrawableRes drawableRes: Int): Drawable? {
    return ResourcesCompat.getDrawable(resources, drawableRes, theme)
}

fun Fragment.colorOf(@ColorRes colorRes: Int): Int {
    return requireContext().colorOf(colorRes)
}

fun Fragment.colorAttrOf(@AttrRes attrRes: Int): Int {
    return requireContext().colorAttrOf(attrRes)
}

fun Fragment.colorStateListOf(@ColorRes colorRes: Int): ColorStateList {
    return requireContext().colorStateListOf(colorRes)
}

fun Fragment.colorStateListFrom(color: Int): ColorStateList {
    return requireContext().colorStateListFrom(color)
}

fun Fragment.colorStateListFromAttrOf(@AttrRes attrRes: Int): ColorStateList {
    return requireContext().colorStateListFromAttrOf(attrRes)
}

fun Fragment.drawableOf(@DrawableRes drawableRes: Int): Drawable? {
    return requireContext().drawableOf(drawableRes)
}


