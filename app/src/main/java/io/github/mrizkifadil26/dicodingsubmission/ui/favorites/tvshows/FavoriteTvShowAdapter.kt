package io.github.mrizkifadil26.dicodingsubmission.ui.favorites.tvshows

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
import io.github.mrizkifadil26.dicodingsubmission.data.tvshows.TvShow
import io.github.mrizkifadil26.dicodingsubmission.ui.detail.tvshows.TvShowDetailActivity
import io.github.mrizkifadil26.dicodingsubmission.util.Config
import kotlinx.android.synthetic.main.item_tv.view.*

class FavoriteTvShowAdapter(private val context: Context): RecyclerView.Adapter<FavoriteTvShowAdapter.FavoriteTvShowViewHolder>() {

    private lateinit var favoriteTvShows: List<TvShow>

    fun setListFavoriteTvShow(favoriteTvShows: List<TvShow>) {
        this.favoriteTvShows = favoriteTvShows
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTvShowViewHolder {
        return FavoriteTvShowViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_tv, parent, false)
        )
    }

    override fun getItemCount(): Int = favoriteTvShows.size

    override fun onBindViewHolder(holder: FavoriteTvShowViewHolder, position: Int) {
        Picasso
            .get()
            .load(Config.IMAGE_URL + favoriteTvShows[position].tvShowPoster)
            .into(holder.favTvPoster)

        holder.favTvTitle.text = favoriteTvShows[position].tvShowTitle
        holder.favTvRating.text = favoriteTvShows[position].tvShowRating.toString()
        holder.favTvContainer.setOnClickListener {
            val intent = Intent(context, TvShowDetailActivity::class.java)
            intent.putExtra(TvShowDetailActivity.EXTRA_TV, favoriteTvShows[position].id)
            context.startActivity(intent)
        }
    }

    inner class FavoriteTvShowViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val favTvContainer: CardView = itemView.tv_container
        val favTvPoster: ImageView = itemView.tv_poster
        val favTvTitle: TextView = itemView.tv_title
        val favTvRating: TextView = itemView.tv_rating
    }

}