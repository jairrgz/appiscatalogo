package com.miprimersistemaweb.appisicatalogo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button

import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.miprimersistemaweb.appisicatalogo.beans.Usuario
import com.miprimersistemaweb.appisicatalogo.repository.UsuarioRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject


class RegistroUsuarioActivity : AppCompatActivity() {

    private lateinit var textName: EditText
    private lateinit var textMail: EditText
    private lateinit var txtPassword: EditText
    private lateinit var txtConfirmPassword: EditText
    private lateinit var buttonRegister: Button
    private lateinit var buttonGoLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)
        Log.i("on create","Configurando interfaz de registro")
    }

    override fun onStart() {
        super.onStart()
        inicializarComponentes()
        initializeEvents()

    }

    private fun inicializarComponentes() {
        textName = findViewById(R.id.etNombre)
        textMail = findViewById(R.id.etCorreo)
        txtPassword = findViewById(R.id.etPassword)
        txtConfirmPassword = findViewById(R.id.etConfirmarPassword)
        buttonRegister = findViewById(R.id.btnRegistrarUsuario)
        buttonGoLogin = findViewById(R.id.btnIrALogin)
    }

    private fun initializeEvents(){
        buttonRegister.setOnClickListener {
            if (validate()) {
                //Enviar correo, nombre y pasword a la API
                /**
                registerAPI()
                 */

            }
        }

    }

    private fun validate(): Boolean {
        var valido = true

        if (textName.text.toString().isBlank()) {
            valido = false
            textName.error = "Debe ingresar su nombre"
        }

        if (textMail.text.toString().isBlank()) {
            valido = false
            textMail.error = "Debe ingresar su correo"
        }

        if (txtPassword.text.toString().isBlank()) {
            valido = false
            txtPassword.error = "Debe ingresar su contraseña"
        }

        if (txtConfirmPassword.text.toString().isBlank()) {
            valido = false
            txtConfirmPassword.error = "Debe confirmar su contraseña"
        }

        if (txtPassword.text.toString() != txtConfirmPassword.text.toString()) {
            txtConfirmPassword.error = "Las contraseñas no coinciden"
        }

        if(!valido){
            Toast.makeText(this,"Error en los datos por favor verificar",Toast.LENGTH_LONG).show()
        }

        return valido

    }

}