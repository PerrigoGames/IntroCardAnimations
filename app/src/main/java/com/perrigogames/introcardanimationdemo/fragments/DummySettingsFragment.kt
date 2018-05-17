package com.perrigogames.introcardanimationdemo.fragments

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import com.perrigogames.introcardanimationdemo.R
import com.perrigogames.introcardanimationdemo.Util
import com.perrigogames.introcardanimationdemo.databinding.FragmentDummySettingsBinding

data class DummySettingsFragmentEntry(val label: TextView, val switch: Switch) {

    constructor(context: Context) : this(TextView(context), Switch(context))

    init {
        label.id = View.generateViewId()
        switch.id = View.generateViewId()
    }
}

class DummySettingsFragment : Fragment() {

    private lateinit var mBinding: FragmentDummySettingsBinding
    private val options = mutableListOf<DummySettingsFragmentEntry>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater!!, R.layout.fragment_dummy_settings, container, false)
        addSetting("share_location", "Enable location sharing", false)
        addSetting("share_wealth", "Enable wealth sharing", false)
        addSetting("app_fires", "Enable app fires", true)
        return mBinding.root
    }

    fun addSetting(key: String, title: String, initialValue: Boolean) {
        val entry = DummySettingsFragmentEntry(activity)
        entry.label.text = title
        entry.switch.isChecked = initialValue
        options.add(entry)

        mBinding.fragmentDummySettings.addView(entry.label)
        mBinding.fragmentDummySettings.addView(entry.switch)

        (entry.label.layoutParams as ConstraintLayout.LayoutParams).let {
            it.width = 0
            val margin = Util.dpToPx(8f, resources).toInt()
            it.setMargins(0, margin, 0, margin)
        }

        val lastLabel: TextView = if (options.isNotEmpty()) options.last().label else mBinding.textSettingsTitle
        Util.constrain(mBinding.fragmentDummySettings, false) {
            it.connect(entry.label.id, ConstraintSet.START, lastLabel.id, ConstraintSet.START)
            it.connect(entry.label.id, ConstraintSet.END, entry.switch.id, ConstraintSet.START)
            it.connect(entry.label.id, ConstraintSet.TOP, lastLabel.id, ConstraintSet.BOTTOM)
            
            it.connect(lastLabel.id, ConstraintSet.BOTTOM, entry.label.id, ConstraintSet.TOP)

            it.connect(entry.switch.id, ConstraintSet.END, mBinding.fragmentDummySettings.id, ConstraintSet.END)
            it.connect(entry.switch.id, ConstraintSet.TOP, entry.label.id, ConstraintSet.TOP)
            it.connect(entry.switch.id, ConstraintSet.BOTTOM, entry.label.id, ConstraintSet.BOTTOM)
        }
    }
}