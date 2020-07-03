package com.example.logineregistro

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class FragmentLogin : AppCompatActivity() {
    lateinit var logEmail: EditText
    lateinit var logPass: EditText
    lateinit var loginFire: Button
    lateinit var txtToRegister: TextView
    lateinit var loginFirebaseAuth: FirebaseAuth
    lateinit var authStateListener: AuthStateListener

    private val viewModelLoginFire by lazy {
        ViewModelProviders.of(this).get(ViewModelLoginFire::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)

        txtToRegister = findViewById(R.id.textToRegister)
        logEmail = findViewById(R.id.etLoginUserEmail)
        logPass = findViewById(R.id.etLoginUserPassword)

        viewModelLoginFire.validinho.observe(this, Observer {
            if (it) {
                startActivity(Intent(this, ActivityHome::class.java))
            } else {
                Toast.makeText(this, "Deu merda", Toast.LENGTH_LONG).show()
            }
        })

        loginFirebaseAuth = FirebaseAuth.getInstance()
        loginFire = findViewById(R.id.login)
        loginFire.setOnClickListener(View.OnClickListener {
            viewModelLoginFire.validaLogin(logEmail.text.toString(), logPass.text.toString())
        })
        authStateListener = AuthStateListener {
            val firebaseUser = viewModelLoginFire.user
            if (firebaseUser != null) {
                val intent = Intent(this, ActivityHome::class.java)
                startActivity(intent)
                Toast.makeText(this, "Bem-vindo ao App!", Toast.LENGTH_SHORT).show()
            }
        }
        txtToRegister.setOnClickListener(View.OnClickListener {
            val intSign = Intent(this, FragmentRegister::class.java)
            startActivity(intSign)
        })
    }

    override fun onStart() {
        super.onStart()
        loginFirebaseAuth.addAuthStateListener(authStateListener)
    }
}