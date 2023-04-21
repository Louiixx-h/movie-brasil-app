package br.com.luishenrique.moviesbrasil.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.favorites.FavoritesFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class HomeActivity : AppCompatActivity(), HomeActivityContract, NavigationBarView.OnItemSelectedListener {

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottomNavigation = findViewById(R.id.bottom_navigation)

        setToolbar()
        setBottomNavigation()
        setFragment(HomeFragment.newInstance())
    }

    override fun setToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbar.title = getString(R.string.app_name)
    }

    override fun setBottomNavigation() {
        bottomNavigation.setOnItemSelectedListener(this)
    }

    override fun setFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.fragmentContainer, fragment, "home")
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menuHome -> {
                setFragment(HomeFragment.newInstance())
            }
            R.id.menuFavorites -> {
                setFragment(FavoritesFragment.newInstance())
            }
        }
        return false
    }
}