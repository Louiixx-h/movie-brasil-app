package br.com.luishenrique.moviesbrasil.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(), HomeActivityContract {

    private val navHostFragment: NavHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.navContainer) as NavHostFragment
    }
    private val appBarConfig: AppBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(R.id.nav_favorites, R.id.nav_home),
            binding.drawerNavigation
        )

    }

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun setupNavController() {
        setupActionBarWithNavController(navHostFragment.navController, appBarConfig)
        binding.navView.setupWithNavController(navHostFragment.navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navHostFragment.navController.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }
}