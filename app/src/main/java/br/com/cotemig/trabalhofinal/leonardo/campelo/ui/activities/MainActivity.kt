package br.com.cotemig.trabalhofinal.leonardo.campelo.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.trabalhofinal.leonardo.campelo.R
import br.com.cotemig.trabalhofinal.leonardo.campelo.models.Filme
import br.com.cotemig.trabalhofinal.leonardo.campelo.models.Usuario
import br.com.cotemig.trabalhofinal.leonardo.campelo.services.RetrofitInitializer
import br.com.cotemig.trabalhofinal.leonardo.campelo.ui.adapters.AdapterFilme
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var filmes: List<Filme> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        obterFilmes()

        var usuario = intent.getSerializableExtra("usuario") as Usuario

        var boasvindas = findViewById<TextView>(R.id.boasvindas)
        boasvindas.text = resources.getString(R.string.boas_vindas, usuario.name)

        var buscar = findViewById<Button>(R.id.botaobusca)
        buscar.setOnClickListener{
            buscarClick()
        }

        var limparBusca = findViewById<Button>(R.id.botaolimparbusca)
        limparBusca.setOnClickListener{
            obterFilmes()
        }

    }

    fun obterFilmes() {
        val s = RetrofitInitializer().serviceFilme()
        val call = s.obterFilmes()

        call.enqueue(object : retrofit2.Callback<List<Filme>> {
            override fun onResponse(call: Call<List<Filme>>, response: Response<List<Filme>>) {
                if (response.code() == 200) {
                    val body = response.body()
                    body?.let {
                        filmes = body
                        mostrarFilmes(body)
                    }
                }
            }

            override fun onFailure(call: Call<List<Filme>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Erro", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun mostrarFilmes(list: List<Filme>) {

        val listFilmes = this.findViewById<RecyclerView>(R.id.listFilmes)
        listFilmes.adapter = AdapterFilme (this, list) {
            val intent = Intent(this, FilmeActivity::class.java)
            intent.putExtra("filme", it)
            startActivity(intent)
        }

        listFilmes.layoutManager = LinearLayoutManager (
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        val helper = PagerSnapHelper()
        listFilmes.setOnFlingListener(null);
        helper.attachToRecyclerView(listFilmes)
    }

    fun buscarClick() {

        var busca = findViewById<EditText>(R.id.campobusca)
        var resultado : List<Filme>?
        this.filmes.let { listFilmes ->
            resultado = listFilmes.filter { it.titulo!!.lowercase().contains(busca.text.toString().lowercase())}

            if (resultado!!.isEmpty()) {
                Toast.makeText(this@MainActivity, "Nenhum resultado encontrado!", Toast.LENGTH_SHORT).show()
                obterFilmes()
            }
            else {
                mostrarFilmes(resultado!!)
            }
        }


    }
}
