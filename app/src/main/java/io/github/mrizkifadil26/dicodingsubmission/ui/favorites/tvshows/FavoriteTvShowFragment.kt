package io.github.mrizkifadil26.dicodingsubmission.ui.favorites.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import io.github.mrizkifadil26.dicodingsubmission.R
import io.github.mrizkifadil26.dicodingsubmission.viewmodel.TvShowViewModel
import kotlinx.android.synthetic.main.fragment_favorite_tv_shows.*

class FavoriteTvShowFragment : Fragment() {

    private lateinit var favoriteTvShowAdapter: FavoriteTvShowAdapter
    private lateinit var favoriteTvShowViewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_tv_shows, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progress_favorite_tv_shows.visibility = View.VISIBLE
        recycler_favorite_tv_shows.apply {
            layoutManager = GridLayoutManager(view.context, 2)
            hasFixedSize()
        }
        favoriteTvShowAdapter = FavoriteTvShowAdapter(view.context)

        favoriteTvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel::class.java)
        favoriteTvShowViewModel.getAllFavoriteTvShows().observe(this, Observer { favoriteTvResults ->
            if (favoriteTvResults.isEmpty()) {
                text_data_tv.text = getString(R.string.no_data)
            } else {
                favoriteTvShowAdapter.apply {
                    setListFavoriteTvShow(favoriteTvResults)
                    notifyDataSetChanged()
                }

                recycler_favorite_tv_shows.apply {
                    adapter = favoriteTvShowAdapter
                }
            }
            progress_favorite_tv_shows.visibility = View.GONE
        })
    }

}