package com.example.ieee

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.ieee.adapters.CommentAdapter
import com.example.ieee.adapters.MovieAdapter
import com.example.ieee.models.Comment
import com.example.ieee.models.Movie
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.SetOptions
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*

class DetailActivity : AppCompatActivity() {

    var comments:ArrayList<Comment> = ArrayList()

    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    var title: String = ""
    var img: Int = 0
    var id: String = ""
    var imgL: Int = 0
    var adapterComment = CommentAdapter(comments, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        title = intent.getStringExtra("nombre")
        img = intent.getIntExtra("img", 0)
        id = intent.getStringExtra("id")
        imgL = intent.getIntExtra("imgL", 0)

        titleN.text = title
        backgroundMovie1.setImageResource(img)
        backgroundMovie2.setImageResource(imgL)

        backButton.setOnClickListener(View.OnClickListener { finish() })

        loadComments()
    }

    fun loadComments(){

        recycler_view_c.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
        adapterComment = CommentAdapter(comments, this);
        recycler_view_c.adapter = adapterComment


        db.collection("comments").whereEqualTo("idC",id).get().addOnCompleteListener {
            if (it.isSuccessful) {
                comments.clear()
                for (documentos: QueryDocumentSnapshot in it.result!!) {
                    val nombreC: String? = documentos.getString("nombreC")
                    val contenidoC: String? = documentos.getString("contenidoC")
                    val idC: String? = documentos.getString("idC")

                    if (nombreC != null && contenidoC != null && idC != null)
                        comments.add(Comment(nombreC, contenidoC, idC))
                    }
                }
            adapterComment.notifyDataSetChanged();
            }
        }

    fun enviar(view: View){

        val comment = Comment(nombreN.text.toString(), contenidoN.text.toString(), id)
        db.collection("comments").document().set(comment, SetOptions.merge()).addOnSuccessListener {
            adapterComment.notifyDataSetChanged();
        }
    }
}
