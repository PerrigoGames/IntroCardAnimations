package com.perrigogames.embeddedfragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.perrigogames.introcardanimationdemo.R

class ParentFragment: Fragment() {

    private var mListener: ChildFragment.OnFragmentInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_parent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val childFragment = ChildFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.child_fragment_container, childFragment).commit()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ChildFragment.OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnFragmentInteractionListener {
        fun messageFromParentFragment(uri: Uri)
    }
}