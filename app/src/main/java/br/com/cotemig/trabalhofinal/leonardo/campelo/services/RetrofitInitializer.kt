package br.com.cotemig.trabalhofinal.leonardo.campelo.services

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

class RetrofitInitializer {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://run.mocky.io/v3/e5b6c024-3bc3-4607-a1cd-b6c2743b2aa6/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitAutenticacao = Retrofit.Builder()
        .baseUrl("https://api.fluo.work/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun serviceFilme(): ServiceFilme {
        return retrofit.create(ServiceFilme::class.java)
    }

    fun serviceUsuario(): ServiceUsuario {
        return retrofitAutenticacao.create(ServiceUsuario::class.java)
    }
}