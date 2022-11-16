package br.com.luishenrique.moviesbrasil.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import br.com.luishenrique.moviesbrasil.R

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_details)

        setToolbar()
        setFragment(DetailsFragment.newInstance())
    }

    private fun setToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val title: TextView = toolbar.findViewById(R.id.toolbar_title)
        title.text = getString(R.string.details)
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.homeFragmentContainer, fragment, "details")
        }
    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, DetailsActivity::class.java)
        }
    }
}