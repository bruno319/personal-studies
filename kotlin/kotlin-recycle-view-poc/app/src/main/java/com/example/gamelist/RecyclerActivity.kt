package com.example.gamelist

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_recycler.*
import kotlinx.android.synthetic.main.content_recycler.*

class RecyclerActivity : AppCompatActivity() {

    var games = arrayListOf(
        "PS4", "Switch", "Xbox", "PC", "PS1", "PS2", "PS3",
        "Mega Drive", "SNES", "Xbox360", "XboxOne", "Polystation", "Dynavision",
        "Atari", "Sega Saturn", "N64", "PSP", "Wii", "Wii U"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var adapter = CustomAdapter(baseContext, games)
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(baseContext, RecyclerView.VERTICAL, false)
    }

}
