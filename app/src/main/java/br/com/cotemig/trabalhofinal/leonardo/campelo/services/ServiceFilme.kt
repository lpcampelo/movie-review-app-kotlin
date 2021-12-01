package br.com.cotemig.trabalhofinal.leonardo.campelo.services

import br.com.cotemig.trabalhofinal.leonardo.campelo.models.Filme
import retrofit2.Call
import retrofit2.http.GET

interface ServiceFilme {

    @GET("filmes")
    fun obterFilmes() : Call<List<Filme>>
}