package com.perrigogames.introcardanimationdemo.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.CardView
import android.util.AttributeSet
import com.perrigogames.introcardanimationdemo.R
import com.perrigogames.introcardanimationdemo.Util
import kotlinx.android.synthetic.main.view_animated_card.view.*


class AnimatedCardView @JvmOverloads constructor(context: Context,
                                                 attrs: AttributeSet? = null,
                                                 defStyleAttr: Int = 0) :
        CardView(context, attrs, defStyleAttr) {

    var iconDrawable: Drawable? = null
        set(v) = image_icon.setImageDrawable(v)

    init {
        radius = 32f
        inflate(context, R.layout.view_animated_card, this)
        reset()
    }

    fun animateIconToCorner() {
        Util.transition(this)
        Util.setZone(layout_animated_card, R.id.image_icon, R.id.zone_icon_corner)
        Util.alignInZone(layout_animated_card, R.id.text_title, R.id.zone_text_title,
                false, false, true, true)
        setContentVisibility(VISIBLE)
    }

    fun reset() {
        Util.setZone(layout_animated_card, R.id.image_icon, R.id.zone_icon_center)
        Util.alignInZone(layout_animated_card, R.id.text_title, R.id.zone_text_center,
                false, false, false, false)
        image_icon.visibility = VISIBLE
        text_title.visibility = VISIBLE
        setContentVisibility(INVISIBLE)
    }

    fun setContentVisibility(visibility: Int) {
        view_divider.visibility = visibility
        scroll_content.visibility = visibility
    }
}