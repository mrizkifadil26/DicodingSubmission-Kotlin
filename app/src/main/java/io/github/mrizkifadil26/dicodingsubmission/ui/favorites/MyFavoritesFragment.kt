package io.github.mrizkifadil26.dicodingsubmission.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.mrizkifadil26.dicodingsubmission.R
import io.github.mrizkifadil26.dicodingsubmission.ui.favorites.movies.FavoriteMovieFragment
import io.github.mrizkifadil26.dicodingsubmission.ui.favorites.tvshows.FavoriteTvShowFragment
import kotlinx.android.synthetic.main.fragment_favorites.*

class MyFavoritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabAdapter = MyFavoriteAdapter(childFragmentManager)
        tabAdapter.addFragment(FavoriteMovieFragment(), "Movies")
        tabAdapter.addFragment(FavoriteTvShowFragment(), "Tv Shows")
        view_pager.adapter = tabAdapter
        favorite_tabs.setupWithViewPager(view_pager)
    }
}