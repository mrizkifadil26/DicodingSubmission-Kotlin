package io.github.mrizkifadil26.dicodingsubmission.ui.movies

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
import io.github.mrizkifadil26.dicodingsubmission.data.movies.MovieResults
import io.github.mrizkifadil26.dicodingsubmission.ui.detail.movies.MovieDetailActivity
import io.github.mrizkifadil26.dicodingsubmission.util.Config
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val context: Context) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private lateinit var movies: List<MovieResults>

    fun setListMovie(movies: List<MovieResults>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_movie,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val indexTruncate = 18
        val title = movies[position].movieTitle

        if (title != null) {
            if (title.length > indexTruncate) {
                holder.movieTitle.text = title.take(indexTruncate).plus("...").plus(" (${movies[position].movieReleaseDate?.let {
                    Config.dateFormatter(
                        it, "yyyy")
                }})")
            } else {
                holder.movieTitle.text = title.plus(" (${movies[position].movieReleaseDate?.let {
                    Config.dateFormatter(
                        it, "yyyy")
                }})")
            }
        }

        holder.movieRating.text = movies[position].movieRating.toString()

        Picasso
            .get()
            .load(Config.IMAGE_URL + movies[position].moviePoster)
            .into(holder.moviePoster)

        holder.movieContainer.setOnClickListener {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movies[position].id)
            context.startActivity(intent)
        }
    }

    inner class MovieViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        val movieContainer: CardView = itemView.movie_container
        val moviePoster: ImageView = itemView.movie_poster
        val movieTitle: TextView = itemView.movie_title
        val movieRating: TextView = itemView.movie_rating
    }

}