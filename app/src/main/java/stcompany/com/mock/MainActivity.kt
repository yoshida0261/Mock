package stcompany.com.mock

import android.app.Activity
import android.support.v7.app.ActionBarActivity
import android.support.v7.app.ActionBar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.support.v4.widget.DrawerLayout
import android.widget.ArrayAdapter
import android.widget.TextView

class MainActivity : ActionBarActivity(), NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private var navigationDrawerFragment: NavigationDrawerFragment? = null

    /**
     * Used to store the last screen title. For use in [.restoreActionBar].
     */
    private var mTitle: CharSequence? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationDrawerFragment = supportFragmentManager.findFragmentById(R.id.navigation_drawer) as NavigationDrawerFragment
        mTitle = title

        // Set up the drawer.
        navigationDrawerFragment!!.setUp(
                R.id.navigation_drawer,
                findViewById(R.id.drawer_layout) as DrawerLayout)
    }

    override fun onNavigationDrawerItemSelected(position: Int) {
        // update the main content by replacing fragments
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(position + 1)).commit()
    }

    fun onSectionAttached(number: Int) {
        when (number) {
            1 -> mTitle = getString(R.string.title_section1)
            2 -> mTitle = getString(R.string.title_section2)
            3 -> mTitle = getString(R.string.title_section3)
        }
    }

    fun restoreActionBar() {
        val actionBar = supportActionBar
        actionBar.navigationMode = ActionBar.NAVIGATION_MODE_STANDARD
        actionBar.setDisplayShowTitleEnabled(true)
        actionBar.title = mTitle
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    class PlaceholderFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View {
            val rootView = inflater!!.inflate(R.layout.fragment_main, container, false)
            return rootView
        }

        override fun onAttach(activity: Activity?) {
            super.onAttach(activity)
            (activity as MainActivity).onSectionAttached(
                    arguments.getInt(ARG_SECTION_NUMBER))
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }

}
