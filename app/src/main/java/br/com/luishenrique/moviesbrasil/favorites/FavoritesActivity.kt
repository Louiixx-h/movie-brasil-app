package br.com.luishenrique.moviesbrasil.favorites

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.databinding.ActivityFavoritesBinding

class FavoritesActivity : AppCompatActivity(), FavoritesActivityContract {

    private lateinit var binding: ActivityFavoritesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        setFragment(FavoritesFragment.newInstance())
    }

    override fun setToolbar() {
        setSupportActionBar(binding.toolbarMain.root)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbarMain.toolbarTitle.text = getString(R.string.favorites)
    }

    override fun setFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.fragmentContainer, fragment, "favorites")
        }
    }

    companion object {
        fun newInstance(context: Activity) = Intent(
            context,
            FavoritesActivity::class.java
        )
    }
}