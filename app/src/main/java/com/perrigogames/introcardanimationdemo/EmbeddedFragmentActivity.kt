package com.perrigogames.introcardanimationdemo

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.perrigogames.embeddedfragment.ChildFragment
import com.perrigogames.embeddedfragment.ParentFragment

class EmbeddedFragmentActivity : AppCompatActivity(), ParentFragment.OnFragmentInteractionListener, ChildFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_fragments)

        // Begin the transaction
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.parent_fragment_container, ParentFragment())
        ft.commit()
    }

    override fun messageFromParentFragment(uri: Uri) {
        Log.i("TAG", "received communication from parent fragment")
    }

    override fun messageFromChildFragment(uri: Uri) {
        Log.i("TAG", "received communication from child fragment")
    }
}