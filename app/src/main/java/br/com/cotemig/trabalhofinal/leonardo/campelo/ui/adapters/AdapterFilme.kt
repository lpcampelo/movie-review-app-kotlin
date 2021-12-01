package br.com.cotemig.trabalhofinal.leonardo.campelo.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import br.com.cotemig.trabalhofinal.leonardo.campelo.R
import br.com.cotemig.trabalhofinal.leonardo.campelo.models.Filme
import coil.load

class AdapterFilme (var context: Context, var list: List<Filme>) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return ""
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view = LayoutInflater.from(context).inflate(R.layout.item_filme, null)

        var poster = view.findViewById<ImageView>(R.id.poster)
        poster.load(list[p0].poster)

        var titulo = view.findViewById<TextView>(R.id.titulo)
        titulo.text = list[p0].titulo

        return view
    }
}