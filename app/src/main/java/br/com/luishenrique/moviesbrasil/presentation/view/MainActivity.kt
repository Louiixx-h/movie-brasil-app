package br.com.luishenrique.moviesbrasil.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.presentation.view.detail.DetailsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar_home)
        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.homeFragmentContainer, DetailsFragment.newInstance())
            addToBackStack(null)
            commit()
        }
    }
}