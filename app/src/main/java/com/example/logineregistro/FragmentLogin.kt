package com.example.logineregistro

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class FragmentLogin : AppCompatActivity() {
    lateinit var logEmail: EditText
    lateinit var logPass: EditText
    lateinit var logButton: Button
    lateinit var txtToRegister: TextView
    lateinit var loginFirebaseAuth: FirebaseAuth
    lateinit var authStateListener: AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)

        txtToRegister = findViewById(R.id.textToRegister)
        loginFirebaseAuth = FirebaseAuth.getInstance()
        logEmail = findViewById(R.id.etLoginUserEmail)
        logPass = findViewById(R.id.etLoginUserPassword)
        logButton = findViewById(R.id.login)
        authStateListener = AuthStateListener {
            val firebaseUser = loginFirebaseAuth?.getCurrentUser()
            if (firebaseUser != null) {
                Toast.makeText(this, "Bem-vindo ao App!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ActivityHome::class.java)
                startActivity(intent)
            }
        }
        logButton.setOnClickListener(View.OnClickListener {
            val email = logEmail.text.toString()
            val pass = logPass.text.toString()
            if (email.isEmpty() || pass.isEmpty()) {
                logEmail.error = "Por favor, preencha os campos"
                logPass.requestFocus()
            } else if (!(email.isEmpty() && pass.isEmpty())) {
                loginFirebaseAuth.signInWithEmailAndPassword(email, pass)?.addOnCompleteListener(this) { task ->
                    if (!task.isSuccessful) {
                        Toast.makeText(this, "E-Mail e Senha estão corretos?", Toast.LENGTH_SHORT).show()
                    } else {
                        val inToHome = Intent(this, ActivityHome::class.java)
                        startActivity(inToHome)
                    }
                }
            }
        })
        txtToRegister.setOnClickListener(View.OnClickListener {
            val intSign = Intent(this, ActivityLoginRegister::class.java)
            startActivity(intSign)
        })
    }

    override fun onStart() {
        super.onStart()
        loginFirebaseAuth.addAuthStateListener(authStateListener)
    }
}