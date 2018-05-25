package com.perrigogames.introcardanimationdemo.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.perrigogames.introcardanimationdemo.databinding.ViewDummyTextBinding


class DummyTextView @JvmOverloads constructor(context: Context,
                                              attrs: AttributeSet? = null,
                                              defStyleAttr: Int = 0)
    : FrameLayout(context, attrs, defStyleAttr) {

    private var mBinding: ViewDummyTextBinding

    init {
        val inflater = LayoutInflater.from(context)
        mBinding = ViewDummyTextBinding.inflate(inflater, this, true)
    }
}
