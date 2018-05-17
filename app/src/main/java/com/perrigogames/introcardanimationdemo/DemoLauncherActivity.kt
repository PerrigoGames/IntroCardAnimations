package com.perrigogames.introcardanimationdemo

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.perrigogames.introcardanimationdemo.fragments.DummySettingsFragment
import com.perrigogames.introcardanimationdemo.fragments.IntroCardTestFragment
import com.perrigogames.introcardanimationdemo.fragments.TextZoneTestFragment
import com.perrigogames.introcardanimationdemo.fragments.WhiteboardFragment
import kotlinx.android.synthetic.main.activity_demo_launcher.*
import kotlinx.android.synthetic.main.app_bar_demo_launcher.*
import kotlinx.android.synthetic.main.content_demo_launcher.*

class DemoLauncherActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_launcher)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.demo_launcher, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_intro_card -> launchFragment(IntroCardTestFragment())
            R.id.nav_text_zones -> launchFragment(TextZoneTestFragment())
            R.id.nav_settings_fragment -> launchFragment(DummySettingsFragment())
            R.id.nav_whiteboard_fragment -> launchFragment(WhiteboardFragment())
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun launchFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.layout_demo_launcher, fragment)
        transaction.commit()
        text_demo_message.visibility = View.GONE
    }
}
