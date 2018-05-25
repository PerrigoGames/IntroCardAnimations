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
import com.perrigogames.introcardanimationdemo.views.DummySettingsView
import com.perrigogames.introcardanimationdemo.views.DummyTextView
import kotlinx.android.synthetic.main.fragment_intro_card_test.*

class IntroCardTestFragment : Fragment() {

    private var activeCard: AnimatedCardView? = null
    private lateinit var mBinding: FragmentIntroCardTestBinding
    private var currentType = 0
    private lateinit var cardTypes: Array<CardType>

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val settingsView = DummySettingsView(context)
        settingsView.addSetting("share_location", "Enable location sharing", false)
        settingsView.addSetting("share_wealth", "Enable wealth sharing", false)
        settingsView.addSetting("app_fires", "Enable app fires", false)
        settingsView.addSetting("app_freezes", "Enable app freezes", false)
        settingsView.addSetting("gdpr_compliance", "Enable GDPR compliance", false)
        settingsView.addSetting("ban_brother", "Ban Donald Trump", true)
        cardTypes = arrayOf(
                CardType("Terms and Conditions",
                        R.drawable.ic_menu_share,
                        DummyTextView(context)),
                CardType("Settings",
                        R.drawable.ic_settings,
                        settingsView) )

        mBinding = DataBindingUtil.inflate(inflater!!, R.layout.fragment_intro_card_test, container, false)!!
        mBinding.fragment = this
        return mBinding.root
    }

    fun onAnimateClicked(v: View) {
        activeCard?.animateToCorner()
    }

    fun onResetClicked(v: View) {
        activeCard?.reset()
    }

    fun onNextClicked(v: View) {
        activeCard?.let {
            animateCardExit(it, {
                it.contentView = null
                layout_activity_main.removeView(it)
            })
        }
        activeCard = AnimatedCardView(context)
        activeCard!!.let { card ->
            val cardType = cardTypes[currentType++]
            if (currentType >= cardTypes.size) {
                currentType = 0
            }

            card.id = View.generateViewId()
            card.title = cardType.title
            card.iconDrawable = resources.getDrawable(cardType.icon, activity.theme)
            card.contentView = cardType.content
            (layout_activity_main).let { layout ->
                layout.addView(card)
                Util.setZone(layout, card.id, R.id.zone_card)
                card.layoutParams.width = 800 //FIXME hardcoded values
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

    data class CardType(val title: String = "Advanced",
                        val icon: Int = R.drawable.ic_settings,
                        val content: View)
}
