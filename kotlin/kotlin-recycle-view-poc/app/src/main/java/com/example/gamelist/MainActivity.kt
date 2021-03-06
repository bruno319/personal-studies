package com.example.gamelist

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    var games = arrayListOf("PS4", "Switch", "Xbox", "PC", "PS1", "PS2", "PS3",
        "Mega Drive", "SNES", "Xbox360", "XboxOne", "Polystation", "Dynavision",
        "Atari", "Sega Saturn", "N64", "PSP", "Wii", "Wii U")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            var intent = Intent(baseContext, RecyclerActivity::class.java)
            startActivity(intent)
        }
//
//        var adapter = ArrayAdapter<String>(baseContext, android.R.layout.simple_list_item_1, games)
//        listView.adapter = adapter
//        listView.setOnItemClickListener { parent, view, position, id ->
//            Toast.makeText(baseContext, "Position: $position", Toast.LENGTH_LONG).show()
//        }
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
