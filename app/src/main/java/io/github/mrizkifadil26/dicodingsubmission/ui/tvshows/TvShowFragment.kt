package io.github.mrizkifadil26.dicodingsubmission.ui.tvshows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import io.github.mrizkifadil26.dicodingsubmission.R
import io.github.mrizkifadil26.dicodingsubmission.data.tvshows.TvShowGenre
import io.github.mrizkifadil26.dicodingsubmission.viewmodel.TvShowViewModel
import kotlinx.android.synthetic.main.fragment_tv_shows.*

class TvShowFragment : Fragment() {

    private lateinit var tvAdapter: TvShowAdapter
    private var tvShowGenres: List<TvShowGenre> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_tv_shows,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress_tv.visibility = View.VISIBLE
        recycler_tv.apply {
            layoutManager = GridLayoutManager(view.context, 2)
            hasFixedSize()
        }
        tvAdapter = TvShowAdapter(view.context)

        val tvViewModel: TvShowViewModel =
            ViewModelProviders.of(this).get(TvShowViewModel::class.java)
        tvViewModel.getAllTvShows().observe(this, Observer { tvShowResults ->
            run {
                tvAdapter.apply {
                    setListTvShow(tvShowResults, tvShowGenres)
                    notifyDataSetChanged()
                }

                recycler_tv.apply {
                    adapter = tvAdapter
                }

                progress_tv.visibility = View.GONE
            }
        })
    }
}
