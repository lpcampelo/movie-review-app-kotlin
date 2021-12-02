package br.com.cotemig.trabalhofinal.leonardo.campelo.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import br.com.cotemig.trabalhofinal.leonardo.campelo.R
import br.com.cotemig.trabalhofinal.leonardo.campelo.models.Usuario
import br.com.cotemig.trabalhofinal.leonardo.campelo.services.RetrofitInitializer
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Response

class SenhaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_senha)

        var recuperarSenha = findViewById<Button>(R.id.botaorecuperarsenha)
        recuperarSenha.setOnClickListener {
            recuperarSenhaClick()
        }


    }

    fun recuperarSenhaClick(){

        var email = findViewById<TextInputEditText>(R.id.emailrecuperarsenha)

        var usuario = Usuario()
        usuario.email = email.text.toString()

        val s = RetrofitInitializer().serviceUsuario()
        val call = s.recuperarSenha(usuario)

        call.enqueue(object : retrofit2.Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.code() == 200) {
                    Toast.makeText(this@SenhaActivity, "Link de recuperação de senha enviado para o e-mail!", Toast.LENGTH_LONG).show()
                    var intent = Intent(this@SenhaActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    Toast.makeText(this@SenhaActivity, "O e-mail informado não está cadastrado!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {

                Toast.makeText(this@SenhaActivity, "Erro!", Toast.LENGTH_LONG).show()

            }
        })
    }

}