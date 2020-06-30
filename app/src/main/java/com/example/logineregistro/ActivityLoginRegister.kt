package com.example.logineregistro


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.google.firebase.auth.FirebaseAuth

class ActivityLoginRegister : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var etRegisterUserEmail: EditText
    lateinit var etRegisterUserPassword: EditText
    lateinit var etRegisterUserName: EditText
    lateinit var etRegisterUserLastName: EditText
    lateinit var btCreateAccount: Button

    private val viewModelRegister by lazy {
        ViewModelProviders.of(this).get(ViewModelRegister::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)
        etRegisterUserName = findViewById(R.id.etRegisterUserName)
        etRegisterUserLastName = findViewById(R.id.etRegisterUserLastName)
        etRegisterUserEmail = findViewById(R.id.etRegisterUserEmail)
        etRegisterUserPassword = findViewById(R.id.etRegisterUserPassword)

        viewModelRegister.validao.observe(this, Observer {
            if(it){
                startActivity(Intent(this, ActivityHome::class.java))
            } else{
                Toast.makeText(this, "Deu merda", Toast.LENGTH_LONG).show()
            }
        })
        firebaseAuth = FirebaseAuth.getInstance()
        btCreateAccount = findViewById(R.id.btCreateAccount)
        btCreateAccount.setOnClickListener(View.OnClickListener {
            viewModelRegister.validarCampo(etRegisterUserName.text.toString()
                    ,etRegisterUserLastName.text.toString()
                    ,etRegisterUserEmail.text.toString()
                    ,etRegisterUserPassword.text.toString())
        })
    }
}