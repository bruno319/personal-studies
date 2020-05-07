package com.example.api_movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var queue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        queue = Volley.newRequestQueue(baseContext)
        searchMovies()
    }

    private fun searchMovies() {
        var request = GsonRequest(
            "http://www.omdbapi.com/?s=James%20Bond&apikey=869ad531",
            Search::class.java, null,
            Response.Listener { response ->
                var adapter = CustomAdapter(baseContext, response.Movies as ArrayList<Movie>)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(baseContext, RecyclerView.VERTICAL, false)
            },
            Response.ErrorListener { error ->
                Toast.makeText(baseContext, "Deu error " + error.message, Toast.LENGTH_LONG).show()
            }
        )
        queue?.add( request )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
