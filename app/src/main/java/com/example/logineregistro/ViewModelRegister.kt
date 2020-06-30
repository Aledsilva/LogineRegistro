package com.example.logineregistro


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ViewModelRegister : ViewModel() {
    val validao = MutableLiveData<Boolean>()
    private val authRegister: FirebaseAuth = FirebaseAuth.getInstance()
    val user get() = authRegister.currentUser

    fun validarCampo(name: String, lastName: String, email: String, pass: String) {
        if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || pass.isEmpty()) {
            validao.postValue(false)
            Log.i("VALIDACAO", "erro ao validar string")
        } else {
            authRegister.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    validao.postValue(true)
                    Log.i("AUTENTICAÇÃO", "Bem-vindo ao App")
                } else {
                    validao.postValue(false)
                    Log.i("AUTENTICAÇÃO", "erro ao AUTENTICAR")
                }
            }
        }
    }
}
