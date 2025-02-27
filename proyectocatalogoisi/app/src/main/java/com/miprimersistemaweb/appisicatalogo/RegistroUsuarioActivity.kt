package com.miprimersistemaweb.appisicatalogo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import com.google.gson.GsonBuilder

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

    }

    private fun inicializarComponentes() {
        textName = findViewById(R.id.etNombre)
        textMail = findViewById(R.id.etCorreo)
        txtPassword = findViewById(R.id.etPassword)
        txtConfirmPassword = findViewById(R.id.etConfirmarPassword)
        buttonRegister = findViewById(R.id.btnRegistrarUsuario)
        buttonGoLogin = findViewById(R.id.btnIrALogin)
    }
/**
    private fun inicializarEventos() {
        buttonRegister.setOnClickListener {
            if (validar()) {
                registroUsuarioApi()
            }
        }
        
        buttonGoLogin.setOnClickListener {
            irLogin()
        }
    }

    private fun validar(): Boolean {
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
            valido = false
            txtConfirmPassword.error = "Las contraseñas no coinciden"
        }

        if (!valido) {
            Toast.makeText(this, "Error en los datos por favor verificar", Toast.LENGTH_LONG).show()
        }

        return valido
    }

    private fun registroUsuarioApi() {
        val usuarioRepository = UsuarioRepository()
        val usuario = Usuario(
            0,
            textName.text.toString(),
            textMail.text.toString(),
            txtPassword.text.toString()
        )
        
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val respuesta = usuarioRepository.registro(usuario)
                withContext(Dispatchers.Main) {
                    if (respuesta.isSuccessful) {
                        val gson = GsonBuilder().setPrettyPrinting().create()
                        val prettyJson = gson.toJson(JsonParser.parseString(respuesta.body()?.string()))
                        val jsonObjeto = JSONObject(prettyJson)
                        
                        if (jsonObjeto.has("error")) {
                            mostrarMensaje(jsonObjeto.getString("error"))
                        } else {
                            mostrarMensaje("Usuario registrado exitosamente")
                            irLogin()
                        }
                        Log.i("Registro", prettyJson)
                    } else {
                        val gson = GsonBuilder().setPrettyPrinting().create()
                        val prettyJson = gson.toJson(JsonParser.parseString(respuesta.errorBody()?.string()))
                        val jsonObjeto = JSONObject(prettyJson)
                        val mensaje = jsonObjeto.getString("message")
                        mostrarMensaje(mensaje)
                        Log.i("respuesta error json", prettyJson)
                    }
                }
            } catch (error: Exception) {
                Log.e("Error Registro", error.message.toString())
            }
        }
    }

    private fun mostrarMensaje(msj: String) {
        Toast.makeText(this, msj, Toast.LENGTH_LONG).show()
    }

    private fun irLogin() {
        val intentLogin = Intent(this, LoginActivity::class.java)
        startActivity(intentLogin)
        finish()
    }
    **/
}