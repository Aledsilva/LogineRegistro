package com.example.logineregistro

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ViewModelLogin : ViewModel() {
    val validinho = MutableLiveData<Boolean>()
    private val authLogin: FirebaseAuth = FirebaseAuth.getInstance()
    val user get() = authLogin.currentUser

    fun validaLogin(eMail: String, passWord: String){
        if (eMail.isEmpty() || passWord.isEmpty()) {
            validinho.postValue(false)
        } else if (!(eMail.isEmpty() && passWord.isEmpty())) {
            authLogin.signInWithEmailAndPassword(eMail, passWord).addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    validinho.postValue(true)
                    Log.i("AUTENTICAÇÃO", "Bem-vindo ao App")
                } else {
                    validinho.postValue(false)
                    Log.i("AUTENTICAÇÃO", "erro ao AUTENTICAR")
                }
            }
        }
    }
}