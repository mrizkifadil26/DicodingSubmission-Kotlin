package io.github.mrizkifadil26.dicodingsubmission.ui.tvshows

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.mrizkifadil26.dicodingsubmission.R
import io.github.mrizkifadil26.dicodingsubmission.viewmodel.TvShowViewModel
import kotlinx.android.synthetic.main.fragment_tv_shows.*

class TvShowFragment : Fragment() {

    private lateinit var tvAdapter: TvShowAdapter

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
        val orientation = resources.configuration.orientation
        progress_tv.visibility = View.VISIBLE
        recycler_tv.apply {
            if (orientation != Configuration.ORIENTATION_LANDSCAPE) {
                layoutManager = GridLayoutManager(view.context, 2)
            } else {
                layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
            }
            hasFixedSize()
        }
        tvAdapter = TvShowAdapter(view.context)

        val tvViewModel: TvShowViewModel =
            ViewModelProviders.of(this).get(TvShowViewModel::class.java)
        tvViewModel.getAllTvShows().observe(this, Observer { tvShowResults ->
            run {
                tvAdapter.apply {
                    setListTvShow(tvShowResults)
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
