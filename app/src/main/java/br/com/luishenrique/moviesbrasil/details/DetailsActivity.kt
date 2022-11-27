package br.com.luishenrique.moviesbrasil.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import br.com.luishenrique.moviesbrasil.R
import br.com.luishenrique.moviesbrasil.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private val movieId: Int? by lazy { intent.extras?.getInt(DETAILS_ID) }
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        setFragment(DetailsFragment.newInstance(movieId!!))
    }

    override fun onBackPressed() {
        finish()
    }

    private fun setToolbar() {
        setSupportActionBar(binding.toolbarMain.root)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.toolbarMain.toolbarTitle.text = getString(R.string.details)
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.homeFragmentContainer, fragment, "details")
        }
    }

    companion object {
        internal const val DETAILS_ID = "details-id"

        fun newInstance(context: Activity, id: Int) = Intent(
            context,
            DetailsActivity::class.java
        ).apply {
            val args = Bundle()
            args.putInt(DETAILS_ID, id)
            putExtras(args)
        }
    }
}