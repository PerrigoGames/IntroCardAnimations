package com.perrigogames.introcardanimationdemo.views

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet.*
import android.util.AttributeSet
import android.view.View
import android.widget.Switch
import android.widget.TextView
import com.perrigogames.introcardanimationdemo.Util

class DummySettingsView @JvmOverloads constructor(context: Context,
                                                  attrs: AttributeSet? = null,
                                                  defStyleAttr: Int = 0)
    : ConstraintLayout(context, attrs, defStyleAttr) {

    private val options = mutableListOf<DummySettingsEntry>()

    fun addSetting(key: String, title: String, initialValue: Boolean) {
        val entry = DummySettingsEntry(key, context)
        entry.label.text = title
        entry.switch.isChecked = initialValue

        addView(entry.label)
        addView(entry.switch)

        val lastLabel: TextView? = if (options.isNotEmpty()) options.last().label else null

        entry.labelParams?.let {
            it.width = 0
            val marginVert = Util.dpToPx(16f, resources).toInt()
            it.setMargins(0, if (lastLabel == null) 0 else marginVert, 0, marginVert)
        }

        Util.constrain(this, false) {
            it.connect(entry.label.id, START, PARENT_ID, START)
            it.connect(entry.label.id, END, entry.switch.id, START)
            if (lastLabel != null) {
                it.connect(entry.label.id, TOP, lastLabel.id, BOTTOM)
            } else {
                it.connect(entry.label.id, TOP, PARENT_ID, TOP)
            }
            
            it.connect(entry.switch.id, END, PARENT_ID, END)
            it.connect(entry.switch.id, TOP, entry.label.id, TOP)
            it.connect(entry.switch.id, BOTTOM, entry.label.id, BOTTOM)
        }
        options.add(entry)
    }

    data class DummySettingsEntry(val key: String, val label: TextView, val switch: Switch) {

        constructor(key: String, context: Context) : this(key, TextView(context), Switch(context))

        init {
            label.id = View.generateViewId()
            switch.id = View.generateViewId()
        }

        val labelParams: ConstraintLayout.LayoutParams?
            get() = label.layoutParams as? ConstraintLayout.LayoutParams
        val switchParams: ConstraintLayout.LayoutParams?
            get() = switch.layoutParams as? ConstraintLayout.LayoutParams
    }
}