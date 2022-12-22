package br.com.luishenrique.moviesbrasil.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.favorites.FavoritesFragment
import br.com.luishenrique.moviesbrasil.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavigation = findViewById(R.id.bottom_navigation)

        setToolbar()
        setBottomNavigation()
        setFragment(HomeFragment.newInstance())
    }

    private fun setToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.title = getString(R.string.app_name)
    }

    private fun setBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.homeFragmentContainer, fragment, "home")
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menuHome -> {
                setFragment(HomeFragment.newInstance())
            }
            R.id.menuSearch -> {
                setFragment(SearchFragment.newInstance())
            }
            R.id.menuFavorites -> {
                setFragment(FavoritesFragment.newInstance())
            }
        }
        return false
    }
}