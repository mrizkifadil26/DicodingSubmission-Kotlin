package io.github.mrizkifadil26.dicodingsubmission.ui.favorites.movies

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
import io.github.mrizkifadil26.dicodingsubmission.data.movies.Movie
import io.github.mrizkifadil26.dicodingsubmission.ui.detail.movies.MovieDetailActivity
import io.github.mrizkifadil26.dicodingsubmission.util.Config
import kotlinx.android.synthetic.main.item_movie.view.*

class FavoriteMovieAdapter(private val context: Context) : RecyclerView.Adapter<FavoriteMovieAdapter.FavoriteMovieViewHolder>() {

    private lateinit var favoriteMovies: List<Movie>

    fun setListFavoriteMovie(favoriteMovies: List<Movie>) {
        this.favoriteMovies = favoriteMovies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
        return FavoriteMovieViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun getItemCount(): Int  = favoriteMovies.size

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        Picasso
            .get()
            .load(Config.IMAGE_URL + favoriteMovies[position].moviePoster)
            .into(holder.favMoviePoster)

        holder.favMovieTitle.text = favoriteMovies[position].movieTitle
        holder.favMovieRating.text = favoriteMovies[position].movieRating.toString()
        holder.favMovieContainer.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, favoriteMovies[position].id)
            context.startActivity(intent)
        }
    }

    inner class FavoriteMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val favMovieContainer: CardView = itemView.movie_container
        val favMoviePoster: ImageView = itemView.movie_poster
        val favMovieTitle: TextView = itemView.movie_title
        val favMovieRating: TextView = itemView.movie_rating

    }
}