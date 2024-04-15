package br.com.imepac.app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class FormCadastro : AppCompatActivity() {

    private lateinit var edit_nome: EditText
    private lateinit var edit_email: EditText
    private lateinit var edit_senha: EditText
    private lateinit var btnCadastrar: Button
    private lateinit var usuarioID : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_cadastro)
        getSupportActionBar()?.hide();

        edit_nome = findViewById(R.id.edit_nome)
        edit_email = findViewById(R.id.edit_email)
        edit_senha = findViewById(R.id.edit_senha)
        btnCadastrar = findViewById(R.id.btn_cadastrar)

        btnCadastrar.setOnClickListener {
            val nome = edit_nome.text.toString().trim()
            val email = edit_email.text.toString().trim()
            val senha = edit_senha.text.toString().trim()

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                val mensagemErro = "Campos não preenchidos,tente novamente"
                val sanckbar = Snackbar.make(it, mensagemErro, Snackbar.LENGTH_LONG);
                sanckbar.show();
            } else {
                cadastarUsuario(it);

            }
        }
    }
    fun cadastarUsuario(it: android.view.View){
        val email = edit_email.text.toString().trim()
        val senha = edit_senha.text.toString().trim()
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha)
            .addOnCompleteListener { task->
                if (task.isSuccessful){
                    salvarDadosUsuario()
                    val mensagemOk = "Cadastro relizado com sucesso"
                    val snackbar = Snackbar.make(it, mensagemOk, Snackbar.LENGTH_LONG);
                    snackbar.show();
                }else{
                    val mensagemErro = "Erro ao cadastrar usuário"
                    val snackbar = Snackbar.make(it,mensagemErro,Snackbar.LENGTH_LONG);
                }
            }
    }

    fun salvarDadosUsuario(){
        val nome = edit_nome.text.toString().trim()
        val db = FirebaseFirestore.getInstance()
        val usuarios = hashMapOf(
            "nome" to nome
         )
        val usuarioID = FirebaseAuth.getInstance().currentUser?.uid

        if (usuarioID != null){
            db.collection("usuários")
                .add(usuarios)
                .addOnSuccessListener {documentReference ->
                    println("Documento adicionado com ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    println("Erro ao adicionar documento: $e")
                }
        }else{
            println("Erro de autenticação")
        }
    }
}

