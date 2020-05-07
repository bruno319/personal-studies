package com.example.procon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var queue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        queue = Volley.newRequestQueue(baseContext)
        readStation()
    }

    private fun readStation() {
        var request = GsonRequest(
            "http://procon.procempa.com.br/atendimento/WSRest/wsProconRanking.php",
            JsonEmpresas::class.java, null,
            Response.Listener { response ->
                listview.adapter = ArrayAdapter<Empresa>(baseContext, android.R.layout.simple_list_item_1, response.empresasMaisReclamadas )
            },
            Response.ErrorListener { error ->
                Toast.makeText(baseContext, "Deu error " + error.message, Toast.LENGTH_LONG ).show()
            }
        )
        queue?.add( request )
    }
}
