package br.com.imepac.app

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FormLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_login)
        getSupportActionBar()?.hide();

        val linkFormCadastro = findViewById<TextView>(R.id.text_tela_cadastro)
        linkFormCadastro.setOnClickListener{
            val telaCadastro = Intent(this,FormCadastro::class.java)
            startActivity(telaCadastro)
        }
    }
}