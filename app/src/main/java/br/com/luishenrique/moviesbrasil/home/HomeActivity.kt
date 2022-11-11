package br.com.luishenrique.moviesbrasil.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import br.com.luishenrique.moviesbrasil.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(findViewById(R.id.toolbar_home))

        bottomNavigation = findViewById(R.id.bottom_navigation)

        setBottomNavigation()
    }

    private fun setBottomNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    private fun setFragment(fragment: Fragment) {
        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.homeFragmentContainer, fragment)
            addToBackStack(null)
            commit()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menuHome -> {
                setFragment(HomeFragment.newInstance())
            }
            R.id.menuSearch -> {
                setFragment(HomeFragment.newInstance())
            }
            R.id.menuFavorites -> {
                setFragment(HomeFragment.newInstance())
            }
        }
        return false
    }
}