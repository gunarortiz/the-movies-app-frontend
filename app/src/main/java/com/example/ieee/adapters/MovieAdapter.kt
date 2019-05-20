package com.example.ieee.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ieee.DetailActivity
import com.example.ieee.R
import com.example.ieee.models.Movie
import kotlinx.android.synthetic.main.item_movie.view.*
import org.jetbrains.anko.toast

class MovieAdapter(val items : ArrayList<Movie>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {


    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvAnimalType?.setImageResource(items.get(position).image)
        holder?.tvAnimalType?.setOnClickListener( {
            val intent = Intent(context, DetailActivity::class.java);
            intent.putExtra("nombre", items.get(position).title)
            intent.putExtra("id", items.get(position).id)
            intent.putExtra("img", items.get(position).image)
            intent.putExtra("imgL", items.get(position).imageL)
            context.startActivity(intent)
        })

    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvAnimalType = view.backgroundMovie

}