package com.perrigogames.introcardanimationdemo.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.perrigogames.introcardanimationdemo.R
import com.perrigogames.introcardanimationdemo.Util
import com.perrigogames.introcardanimationdemo.databinding.FragmentIntroCardTestBinding
import com.perrigogames.introcardanimationdemo.views.AnimatedCardView
import kotlinx.android.synthetic.main.fragment_intro_card_test.*

class IntroCardTestFragment : Fragment() {

    private var activeCard: AnimatedCardView? = null
    private lateinit var mBinding: FragmentIntroCardTestBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater!!, R.layout.fragment_intro_card_test, container, false)!!
        mBinding.fragment = this
        return mBinding.root
    }

    fun onAnimateClicked(v: View) {
        activeCard?.animateIconToCorner()
    }

    fun onResetClicked(v: View) {
        activeCard?.reset()
    }

    fun onNextClicked(v: View) {
        activeCard?.let {
            animateCardExit(it, { layout_activity_main.removeView(it) })
        }
        activeCard = AnimatedCardView(context)
        activeCard!!.let { card ->
            card.id = View.generateViewId()
            card.iconDrawable = resources.getDrawable(R.drawable.ic_settings, activity.theme)
            (layout_activity_main).let { layout ->
                layout.addView(card)
                Util.setZone(layout, card.id, R.id.zone_card)
                card.layoutParams.width = 800
                card.layoutParams.height = 1000
            }
            animateCardEnter(card)
        }
    }

    fun animateCardEnter(card: View) {
        card.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_card_slide_in))
    }

    fun animateCardExit(card: View, callback: (() -> Unit)? = null) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.anim_card_slide_out)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {}
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                layout_activity_main.post(callback)
            }
        })
        card.startAnimation(animation)
    }
}
