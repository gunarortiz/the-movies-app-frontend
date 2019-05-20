package com.example.ieee

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.ieee.adapters.MovieAdapter
import com.example.ieee.models.Movie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var movies1:ArrayList<Movie> = ArrayList()
    var movies2:ArrayList<Movie> = ArrayList()
    var movies3:ArrayList<Movie> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()

        recycler_view_1.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        recycler_view_1.adapter = MovieAdapter(movies1, this)

        recycler_view_2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        recycler_view_2.adapter = MovieAdapter(movies2, this)

        recycler_view_3.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        recycler_view_3.adapter = MovieAdapter(movies3, this)


    }

    fun loadData(){
        var m = Movie("Captain Marvel", "1", R.drawable.t_marvel, R.drawable.marvel)
        movies1.add(m)
        movies1.add(Movie("Black Panther", "2", R.drawable.t_panter, R.drawable.panter))
        movies1.add(Movie("Ant-Man and the Wasp", "3", R.drawable.t_ant, R.drawable.ant))
        movies1.add(Movie("Vemon", "1", R.drawable.t_venom, R.drawable.venom))
        movies1.add(Movie("Los Increibles 2", "4", R.drawable.t_incre, R.drawable.incre))

        movies2.add(Movie("End Game", "5", R.drawable.t_avenger, R.drawable.avenger))
        movies2.add(Movie("Batman", "6", R.drawable.t_bat, R.drawable.bat))
        movies2.add(Movie("Hellboy", "7", R.drawable.t_hell, R.drawable.hell))
        movies2.add(Movie("Jonh", "9", R.drawable.t_john, R.drawable.john))
        movies2.add(Movie("Deadpool 2", "12", R.drawable.t_dead, R.drawable.dead))

        movies3.add(Movie("Rocketman", "10", R.drawable.t_rock, R.drawable.rock))
        movies3.add(Movie("Yestarday", "11", R.drawable.t_yester, R.drawable.yester))
        movies3.add(Movie("Aladin", "8", R.drawable.t_ala, R.drawable.ala))
        movies3.add(Movie("Spider-Man", "13", R.drawable.t_spider, R.drawable.spider))
        movies3.add(Movie("El Padrino", "14", R.drawable.t_padrino, R.drawable.padrino))
    }

}
