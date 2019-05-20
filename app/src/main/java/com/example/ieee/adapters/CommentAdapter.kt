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
import com.example.ieee.models.Comment
import com.example.ieee.models.Movie
import kotlinx.android.synthetic.main.item_comment.view.*
import kotlinx.android.synthetic.main.item_movie.view.*
import org.jetbrains.anko.toast

class CommentAdapter(val items : ArrayList<Comment>, val context: Context) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {


    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.nombre?.text = (items.get(position).nombreC)
        holder?.contenido?.text = (items.get(position).contenidoC)

    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val nombre = view.nombreC
        val contenido = view.contenidoC

    }
}


