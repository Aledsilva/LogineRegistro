package com.example.logineregistro

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class ActivityHome : AppCompatActivity() {
    private val authRegister: FirebaseAuth = FirebaseAuth.getInstance()
    lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)
        btnLogout = findViewById(R.id.logOut)
        btnLogout.setOnClickListener(View.OnClickListener {
            authRegister.signOut()
            Toast.makeText(this, "Tchau!", Toast.LENGTH_SHORT).show()
            finish()
            val inToMain = Intent(this, FragmentLogin::class.java)
            startActivity(inToMain)
        })
    }
}