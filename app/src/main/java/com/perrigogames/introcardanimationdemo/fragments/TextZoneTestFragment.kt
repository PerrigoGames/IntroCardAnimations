package com.perrigogames.introcardanimationdemo.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.perrigogames.introcardanimationdemo.R
import com.perrigogames.introcardanimationdemo.Util
import com.perrigogames.introcardanimationdemo.databinding.FragmentTextZoneTestBinding
import kotlinx.android.synthetic.main.fragment_text_zone_test.*

class TextZoneTestFragment : Fragment() {

    private lateinit var mBinding: FragmentTextZoneTestBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater!!, R.layout.fragment_text_zone_test, container, false)
        mBinding.fragment = this
        return mBinding.root
    }

    fun onCenterClicked(v: View) {
        constrainText(false, false, false, false)
    }

    fun onSideClicked(v: View) {
        constrainText(true, false, false, false)
    }

    fun onCornerClicked(v: View) {
        constrainText(true, true, false, false)
    }

    private fun constrainText(start: Boolean, top: Boolean, end: Boolean, bottom: Boolean) {
        Util.transition(layout_text_zone_test)
        Util.alignInZone(layout_text_zone_test, R.id.text_content, R.id.zone_text, start, top, end, bottom)
        (text_content.layoutParams as ConstraintLayout.LayoutParams).let {
            it.width = ConstraintLayout.LayoutParams.WRAP_CONTENT
            it.height = ConstraintLayout.LayoutParams.WRAP_CONTENT
        }
    }
}
