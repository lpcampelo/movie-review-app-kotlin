package br.com.cotemig.trabalhofinal.leonardo.campelo.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import br.com.cotemig.trabalhofinal.leonardo.campelo.R
import br.com.cotemig.trabalhofinal.leonardo.campelo.models.Filme
import coil.load

class FilmeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filme)

        var filme = intent.getSerializableExtra("filme") as Filme

        var titulo = findViewById<TextView>(R.id.titulo)
        titulo.text = filme.titulo

        var poster = findViewById<ImageView>(R.id.poster)
        poster.load(filme.poster)

        var nota = findViewById<TextView>(R.id.nota)
        nota.text = filme.nota

        var genero = findViewById<TextView>(R.id.genero)
        genero.text = filme.genero

        var avaliacao = findViewById<TextView>(R.id.avaliacao)
        avaliacao.text = filme.avaliacao

    }
}