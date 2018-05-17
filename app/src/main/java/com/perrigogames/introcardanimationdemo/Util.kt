package com.perrigogames.introcardanimationdemo

import android.content.res.Resources
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.util.TypedValue
import android.view.ViewGroup
import com.transitionseverywhere.*

object Util {

    fun setZone(root: ConstraintLayout, itemId: Int, zoneId: Int,
                start: Boolean = true,
                top: Boolean = true,
                end: Boolean = true,
                bottom: Boolean = true,
                fresh: Boolean = false) {
        val set = ConstraintSet()
        if (!fresh)
            set.clone(root)

        if (start)
            set.connect(itemId, ConstraintSet.START, zoneId, ConstraintSet.START)
        if (end)
            set.connect(itemId, ConstraintSet.END, zoneId, ConstraintSet.END)
        if (top)
            set.connect(itemId, ConstraintSet.TOP, zoneId, ConstraintSet.TOP)
        if (bottom)
            set.connect(itemId, ConstraintSet.BOTTOM, zoneId, ConstraintSet.BOTTOM)
        set.applyTo(root)
        root.requestLayout()
    }

    fun centerInZone(root: ConstraintLayout, itemId: Int, zoneId: Int, block: ((ConstraintSet) -> Unit)? = null) {
        constrain(root, block = {
            it.connect(itemId, ConstraintSet.START, zoneId, ConstraintSet.START)
            it.connect(itemId, ConstraintSet.END, zoneId, ConstraintSet.END)
            it.connect(itemId, ConstraintSet.TOP, zoneId, ConstraintSet.TOP)
            it.connect(itemId, ConstraintSet.BOTTOM, zoneId, ConstraintSet.BOTTOM)
            block?.invoke(it)
        })
    }

    fun alignInZone(root: ConstraintLayout, itemId: Int, zoneId: Int,
                    start: Boolean = true,
                    top: Boolean = true,
                    end: Boolean = true,
                    bottom: Boolean = true) {
        constrain(root, fresh = false, block = {
            it.connect(itemId, ConstraintSet.START, zoneId, ConstraintSet.START)
            it.connect(itemId, ConstraintSet.END, zoneId, ConstraintSet.END)
            it.connect(itemId, ConstraintSet.TOP, zoneId, ConstraintSet.TOP)
            it.connect(itemId, ConstraintSet.BOTTOM, zoneId, ConstraintSet.BOTTOM)
            if (start && !end) {
                it.setHorizontalBias(itemId, 0f)
            } else if (!start && end) {
                it.setHorizontalBias(itemId, 1f)
            } else {
                it.setHorizontalBias(itemId, 0.5f)
            }
            if (top && !bottom) {
                it.setVerticalBias(itemId, 0f)
            } else if (!top && bottom) {
                it.setVerticalBias(itemId, 1f)
            } else {
                it.setVerticalBias(itemId, 0.5f)
            }
        })
    }

    inline fun constrain(root: ConstraintLayout, fresh: Boolean = true, block: (ConstraintSet) -> Unit) {
        val set = ConstraintSet()
        if (!fresh) {
            set.clone(root)
        }
        block(set)
        set.applyTo(root)
        root.requestLayout()
    }

    fun transition(root: ViewGroup) {
        val transition = TransitionSet()
        transition.addTransition(ChangeBounds())
        transition.addTransition(Fade())
        transition.addTransition(ChangeText())
        TransitionManager.go(Scene(root), transition)
    }

    fun dpToPx(dp: Float, res: Resources): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.displayMetrics)
    }
}