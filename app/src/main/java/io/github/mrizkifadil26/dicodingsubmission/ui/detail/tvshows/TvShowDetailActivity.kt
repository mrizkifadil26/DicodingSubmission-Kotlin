package io.github.mrizkifadil26.dicodingsubmission.ui.detail.tvshows

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import io.github.mrizkifadil26.dicodingsubmission.R
import io.github.mrizkifadil26.dicodingsubmission.data.tvshows.TvShow
import io.github.mrizkifadil26.dicodingsubmission.util.Config
import io.github.mrizkifadil26.dicodingsubmission.viewmodel.TvShowViewModel
import kotlinx.android.synthetic.main.activity_tv_show_detail.*

class TvShowDetailActivity : AppCompatActivity() {

    private var tvShowViewModel: TvShowViewModel? = null
    private var tvShowId: Int = 0
    private lateinit var tvShowResult: TvShow
    private var isClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show_detail)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        tvShowId = intent.getIntExtra(EXTRA_TV, 0)

        progress_detail_tv.visibility = View.VISIBLE

        tvShowViewModel = ViewModelProviders.of(this@TvShowDetailActivity).get(TvShowViewModel::class.java)
        tvShowViewModel?.getFavoriteTvShow(tvShowId)?.observe(this, Observer { tvResults ->
            if (tvResults != null) {
                fab_fav_tv_detail.setImageResource(R.drawable.ic_favorite)
                isClicked = true

                Picasso.get()
                    .load(Config.IMAGE_URL + tvResults.tvShowPoster)
                    .into(tv_detail_poster)

                Picasso.get()
                    .load(Config.BACKDROP_URL + tvResults.tvShowBackdrop)
                    .into(tv_detail_backdrop)

                tv_detail_title.text = tvResults.tvShowTitle.plus(" (" + tvResults.tvShowReleaseDate?.let {
                    Config.dateFormatter(
                        it, "yyyy")
                } + ")")
//            tv_detail_genre.text = Config.tvGenreStringBuilder(tvDetail)
                tv_detail_release_date.text =
                    tvResults.tvShowReleaseDate?.let { Config.dateFormatter(it, "d MMM yyyy") }
                tv_detail_rating.text = tvResults.tvShowRating.toString()
                tv_detail_seasons.text = tvResults.tvShowSeasons.toString()
                tv_detail_episodes.text = tvResults.tvShowEpisodes.toString()
                tv_detail_overview.text = tvResults.tvShowDescription

                progress_detail_tv.visibility = View.GONE

                tvShowResult = tvResults
            } else {
                tvShowViewModel?.getTvShowById(tvShowId)?.observe(this, Observer { remoteTvResults ->
                    fab_fav_tv_detail.setImageResource(R.drawable.ic_favorite_border)
                    isClicked = false

                    Picasso.get()
                        .load(Config.IMAGE_URL + remoteTvResults.tvShowPoster)
                        .into(tv_detail_poster)

                    Picasso.get()
                        .load(Config.BACKDROP_URL + remoteTvResults.tvShowBackdrop)
                        .into(tv_detail_backdrop)

                    tv_detail_title.text = remoteTvResults.tvShowTitle.plus(" (" + remoteTvResults.tvShowReleaseDate?.let {
                        Config.dateFormatter(
                            it, "yyyy")
                    } + ")")
//            tv_detail_genre.text = Config.tvGenreStringBuilder(tvDetail)
                    tv_detail_release_date.text =
                        remoteTvResults.tvShowReleaseDate?.let { Config.dateFormatter(it, "d MMM yyyy") }
                    tv_detail_rating.text = remoteTvResults.tvShowRating.toString()
                    tv_detail_seasons.text = remoteTvResults.tvShowSeasons.toString()
                    tv_detail_episodes.text = remoteTvResults.tvShowEpisodes.toString()

                    tv_detail_overview.text = remoteTvResults.tvShowDescription

                    progress_detail_tv.visibility = View.GONE

                    tvShowResult = remoteTvResults
                })
            }
        })

        fab_fav_tv_detail.setOnClickListener { view ->
            isClicked = if (!isClicked) {
                tvShowViewModel?.insertFavoriteTvShow(tvShowResult)
                fab_fav_tv_detail.setImageResource(R.drawable.ic_favorite)
                Snackbar.make(view, "Added to Favorite List", Snackbar.LENGTH_SHORT).show()
                true
            } else {
                tvShowViewModel?.deleteFavoriteTvShow(tvShowResult)
                fab_fav_tv_detail.setImageResource(R.drawable.ic_favorite_border)
                Snackbar.make(view, "Removed from Favorite List", Snackbar.LENGTH_SHORT).show()
                false
            }
        }
    }

    companion object {
        const val EXTRA_TV = "extra_tv"
    }
}
