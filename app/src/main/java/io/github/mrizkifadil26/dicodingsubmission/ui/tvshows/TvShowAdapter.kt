package io.github.mrizkifadil26.dicodingsubmission.ui.tvshows

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.github.mrizkifadil26.dicodingsubmission.R
import io.github.mrizkifadil26.dicodingsubmission.data.tvshows.TvShowGenre
import io.github.mrizkifadil26.dicodingsubmission.data.tvshows.TvShowResults
import io.github.mrizkifadil26.dicodingsubmission.ui.detail.tvshows.TvShowDetailActivity
import io.github.mrizkifadil26.dicodingsubmission.util.Config
import kotlinx.android.synthetic.main.item_tv.view.*

class TvShowAdapter(private val context: Context)
    : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private lateinit var tvShows: List<TvShowResults>
    private lateinit var genres: List<TvShowGenre>

    fun setListTvShow(tvShows: List<TvShowResults>, genres: List<TvShowGenre>) {
        this.tvShows = tvShows
        this.genres = genres
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        return TvShowViewHolder(
            LayoutInflater.from(context).inflate(
                    R.layout.item_tv,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount(): Int = tvShows.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val indexTruncate = 20
        val tvTitle = tvShows[position].tvShowTitle

        if (tvTitle.length > indexTruncate) {
            holder.tvTitle.text = tvTitle.take(indexTruncate).plus("...").plus(" (${Config.dateFormatter(tvShows[position].tvReleaseDate, "yyyy")})")
        } else {
            holder.tvTitle.text = tvTitle.plus(" (${Config.dateFormatter(tvShows[position].tvReleaseDate, "yyyy")})")
        }

        holder.tvRating.text = tvShows[position].tvShowRating.toString()

        Picasso
            .get()
            .load(Config.IMAGE_URL + tvShows[position].tvShowPoster)
            .into(holder.tvPoster)

        holder.tvContainer.setOnClickListener {
            val intent = Intent(context, TvShowDetailActivity::class.java)
            intent.putExtra(TvShowDetailActivity.EXTRA_TV, tvShows[position].id)
            context.startActivity(intent)
        }
    }

    inner class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvContainer: CardView = itemView.tv_container
        val tvPoster: ImageView = itemView.tv_poster
        val tvTitle: TextView = itemView.tv_title
        val tvRating: TextView = itemView.tv_rating
    }
}