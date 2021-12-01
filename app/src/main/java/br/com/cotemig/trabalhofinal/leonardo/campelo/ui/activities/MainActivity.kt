package br.com.cotemig.trabalhofinal.leonardo.campelo.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import br.com.cotemig.trabalhofinal.leonardo.campelo.R
import br.com.cotemig.trabalhofinal.leonardo.campelo.models.Filme
import br.com.cotemig.trabalhofinal.leonardo.campelo.models.Usuario
import br.com.cotemig.trabalhofinal.leonardo.campelo.services.RetrofitInitializer
import br.com.cotemig.trabalhofinal.leonardo.campelo.ui.adapters.AdapterFilme
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        obterFilmes()

        var usuario = intent.getSerializableExtra("usuario") as Usuario

        var boasvindas = findViewById<TextView>(R.id.boasvindas)
        boasvindas.text = resources.getString(R.string.boas_vindas, usuario.name)

    }

    fun obterFilmes() {
        val s = RetrofitInitializer().serviceFilme()
        val call = s.obterFilmes()

        call.enqueue(object : retrofit2.Callback<List<Filme>> {
            override fun onResponse(call: Call<List<Filme>>, response: Response<List<Filme>>) {
                if (response.code() == 200) {
                    val body = response.body()
                    body?.let {mostrarFilmes(body)}
                }
            }

            override fun onFailure(call: Call<List<Filme>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Erro", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun mostrarFilmes(list: List<Filme>) {

        var listFilmes = findViewById<ListView>(R.id.listFilmes)
        listFilmes.adapter = AdapterFilme(this, list)
    }
}
