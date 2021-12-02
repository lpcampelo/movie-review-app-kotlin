package br.com.cotemig.trabalhofinal.leonardo.campelo.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.trabalhofinal.leonardo.campelo.R
import br.com.cotemig.trabalhofinal.leonardo.campelo.models.Filme
import coil.load

class AdapterFilme (var context: Context, var list: List<Filme>, var onClick: (Filme) -> Unit):
RecyclerView.Adapter<AdapterFilme.FilmeHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_filme, parent, false)
        return FilmeHolder(view)
    }

    override fun onBindViewHolder(holder: FilmeHolder, position: Int) {
        holder.bind(list[position], onClick)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class FilmeHolder(var view: View) : RecyclerView.ViewHolder(view) {

        fun bind(filme: Filme, onClick: (Filme) -> Unit) {
            var card = view.findViewById<CardView>(R.id.filmecard)
            card.setOnClickListener { onClick(filme)}

            var titulo = view.findViewById<TextView>(R.id.titulo)
            titulo.text = filme.titulo

            var poster = view.findViewById<ImageView>(R.id.poster)
            poster.load(filme.poster)

            var nota = view.findViewById<TextView>(R.id.nota)
            nota.text = filme.nota

            var genero = view.findViewById<TextView>(R.id.genero)
            genero.text = filme.genero
        }
    }
}