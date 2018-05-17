package com.perrigogames.introcardanimationdemo.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.perrigogames.introcardanimationdemo.R
import kotlinx.android.synthetic.main.fragment_dummy_text.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DummyTextFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [DummyTextFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DummyTextFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            text_content.text = arguments.getString(ARG_TEXT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_dummy_text, container, false)
    }

    companion object {
        private val ARG_TEXT = "text"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @param text the text to display in the fragment
         */
        fun newInstance(text: String): DummyTextFragment {
            val fragment = DummyTextFragment()
            val args = Bundle()
            args.putString(ARG_TEXT, text)
            fragment.arguments = args
            return fragment
        }
    }
}
