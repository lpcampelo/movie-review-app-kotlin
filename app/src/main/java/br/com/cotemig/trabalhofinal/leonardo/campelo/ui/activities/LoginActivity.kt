package br.com.cotemig.trabalhofinal.leonardo.campelo.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import br.com.cotemig.trabalhofinal.leonardo.campelo.R
import br.com.cotemig.trabalhofinal.leonardo.campelo.models.Usuario
import br.com.cotemig.trabalhofinal.leonardo.campelo.services.RetrofitInitializer
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var login = findViewById<Button>(R.id.botaologin)
        login.setOnClickListener {
            loginClick()
        }

        var cadastrar = findViewById<TextView>(R.id.criarconta)
        cadastrar.setOnClickListener{
            var intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        var recuperarSenha = findViewById<TextView>(R.id.esqueciminhasenha)
        recuperarSenha.setOnClickListener{
            var intent = Intent(this, SenhaActivity::class.java)
            startActivity(intent)
        }

    }

    fun loginClick() {

        var email = findViewById<TextInputEditText>(R.id.email)
        var senha = findViewById<TextInputEditText>(R.id.senha)

        var usuario = Usuario()
        usuario.email = email.text.toString()
        usuario.password = senha.text.toString()

        val s = RetrofitInitializer().serviceUsuario()
        val call = s.auth(usuario)

        call.enqueue(object : retrofit2.Callback<Usuario>{
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if(response.code() == 200){
                    response.body()?.let{
                    var intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("usuario", it)
                    startActivity(intent)
                    finish()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Usuário ou senha inválidos!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Erro", Toast.LENGTH_LONG).show()
            }
        })



    }
}