package br.com.cotemig.trabalhofinal.leonardo.campelo.services

import br.com.cotemig.trabalhofinal.leonardo.campelo.models.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceUsuario {

    @POST("account/auth")
    fun auth(@Body usuario: Usuario) : Call<Usuario>

    @POST("account")
    fun cadastrar(@Body account: Usuario): Call<Usuario>

    @POST("account/forgot")
    fun recuperarSenha (@Body account: Usuario): Call<Usuario>
}