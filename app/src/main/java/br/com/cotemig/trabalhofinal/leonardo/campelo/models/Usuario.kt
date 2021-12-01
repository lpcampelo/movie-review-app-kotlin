package br.com.cotemig.trabalhofinal.leonardo.campelo.models

import java.io.Serializable

class Usuario: Serializable {
    var id: String? = null
    var email: String? = null
    var password: String? = null
    var name: String? = null
    var token: String? = null
}