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

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        var cadastrar = findViewById<Button>(R.id.botaocadastrar)
        cadastrar.setOnClickListener {
            cadastrarClick()
        }
    }

    fun cadastrarClick() {
        var nome = findViewById<TextInputEditText>(R.id.cadastronome)
        var email = findViewById<TextInputEditText>(R.id.cadastroemail)
        var senha = findViewById<TextInputEditText>(R.id.senha)

        var usuario = Usuario()
        usuario.name = nome.text.toString()
        usuario.email = email.text.toString()
        usuario.password = senha.text.toString()

        val s = RetrofitInitializer().serviceUsuario()
        val call = s.cadastrar(usuario)

        call.enqueue(object : retrofit2.Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.code() == 200) {
                    Toast.makeText(this@CadastroActivity, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show()
                    var intent = Intent(this@CadastroActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else
                {
                    Toast.makeText(this@CadastroActivity, "Dados já cadastrados!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {

            }
        })
    }
}