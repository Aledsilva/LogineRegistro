package com.example.logineregistro

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ViewModelLogin : ViewModel() {
    /*val validinho = MutableLiveData<Boolean>()
    private val authLogin: FirebaseAuth = FirebaseAuth.getInstance()
    val user get() = authLogin.currentUser
    val firebaseUser = loginFirebaseAuth?.getCurrentUser()
    lateinit var loginFirebaseAuth: FirebaseAuth

    fun validaLogin(eMail: String, passWord: String){

        if (firebaseUser != null) {
            validinho.postValue(true)
            Log.i("VALIDACAO", "erro ao validar string")
        }else if (eMail.isNullOrEmpty() ||passWord.isNullOrEmpty()) {
            validinho.postValue(false)
            Log.i("VALIDACAO", "erro ao validar string")
        } else {
            authLogin.createUserWithEmailAndPassword(eMail, passWord).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        validinho.postValue(true)
                    } else {
                        validinho.postValue(false)
                        Log.i("AUTENTICAÇÃO", "erro ao AUTENTICAR")
                    }
            }
        }
    }*/
}