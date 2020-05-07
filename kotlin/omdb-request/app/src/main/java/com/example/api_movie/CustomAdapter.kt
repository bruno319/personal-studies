package com.example.api_movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.list_item_movie.view.*

class CustomAdapter(var context: Context, var movies: ArrayList<Movie>) :
    androidx.recyclerview.widget.RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
        return ViewHolder(v, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateList(list: ArrayList<Movie>) {
        movies = list
        notifyDataSetChanged()
    }

    fun clearList() {
        movies.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, var ctx: Context) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

        fun bindItems(movie: Movie) {
            itemView.year.text = movie.Year
            itemView.title.text = movie.Title
            Picasso.get()
                .load(movie.Poster)
                .fit()
                .centerCrop()
                .into(itemView.movieImage)
        }
    }
}