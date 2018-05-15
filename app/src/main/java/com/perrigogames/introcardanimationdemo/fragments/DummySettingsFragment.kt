package com.perrigogames.introcardanimationdemo.fragments

import android.app.Fragment
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import com.perrigogames.introcardanimationdemo.R
import com.perrigogames.introcardanimationdemo.Util
import com.perrigogames.introcardanimationdemo.databinding.FragmentDummySettingsBinding
import kotlinx.android.synthetic.main.fragment_dummy_settings.*

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
        addSetting("share_location", "Enable location sharing")
        addSetting("share_wealth", "Enable wealth sharing")
        addSetting("app_fires", "Enable app fires")
        return mBinding.root
    }

    fun addSetting(key: String, title: String, initialValue: Boolean) {
        val entry = DummySettingsFragmentEntry(activity)
        entry.label.text = title
        entry.switch.isChecked = initialValue

        val lastLabel: TextView = if (options.isNotEmpty()) options.last().label else text_settings_title
        Util.constrain(mBinding.fragmentDummySettings) {
            it.connect(entry.label.id, ConstraintSet.START, lastLabel.id, ConstraintSet.START)
            it.connect(entry.label.id, ConstraintSet.END, guideline_control_divider.id, ConstraintSet.END)
            it.connect(entry.label.id, ConstraintSet.TOP, lastLabel.id, ConstraintSet.BOTTOM)
            it.connect(entry.label.id, ConstraintSet.START, lastLabel.id, ConstraintSet.START)
            it.connect(entry.label.id, ConstraintSet.END, guideline_control_divider.id, ConstraintSet.END)
            it.connect(entry.label.id, ConstraintSet.TOP, lastLabel.id, ConstraintSet.TOP)
            it.connect(entry.label.id, ConstraintSet.BOTTOM, lastLabel.id, ConstraintSet.BOTTOM)
        }
        mBinding.fragmentDummySettings.addView(entry.label)
        mBinding.fragmentDummySettings.addView(entry.switch)
    }
}