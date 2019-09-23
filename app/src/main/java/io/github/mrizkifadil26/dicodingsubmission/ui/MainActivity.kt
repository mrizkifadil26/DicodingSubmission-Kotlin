package io.github.mrizkifadil26.dicodingsubmission.ui

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.github.mrizkifadil26.dicodingsubmission.R
import io.github.mrizkifadil26.dicodingsubmission.ui.favorites.MyFavoritesFragment
import io.github.mrizkifadil26.dicodingsubmission.ui.movies.MovieFragment
import io.github.mrizkifadil26.dicodingsubmission.ui.tvshows.TvShowFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.apply {
            title = getString(R.string.app_name)
        }
        setSupportActionBar(toolbar)
        nav_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState == null) {
            nav_view.selectedItemId = R.id.navigation_movies
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val fragment: Fragment

        when (item.itemId) {
            R.id.navigation_movies -> {
                fragment = MovieFragment()
                supportFragmentManager.beginTransaction().replace(
                    R.id.nav_host_fragment,
                    fragment,
                    fragment.javaClass.simpleName
                ).commit()
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_tv -> {
                fragment = TvShowFragment()
                supportFragmentManager.beginTransaction().replace(
                    R.id.nav_host_fragment,
                    fragment,
                    fragment.javaClass.simpleName
                ).commit()
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_favorites -> {
                fragment = MyFavoritesFragment()
                supportFragmentManager.beginTransaction().replace(
                    R.id.nav_host_fragment,
                    fragment,
                    fragment.javaClass.simpleName
                ).detach(fragment)
                    .attach(fragment)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_language) {
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}