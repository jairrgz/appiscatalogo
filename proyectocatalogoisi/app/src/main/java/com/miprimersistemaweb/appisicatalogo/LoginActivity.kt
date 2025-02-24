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


class LoginActivity : AppCompatActivity() {

    private lateinit var txtCorreo:EditText
    private lateinit var txtPassword:EditText
    private lateinit var btnLogin:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.i("on create","configura la interfaz principal, se crea app")
    }

    override fun onStart() {
        super.onStart()
        inicializarComponentes()
        inicializarEventos()

    }
    private fun inicializarComponentes(){
        txtCorreo = findViewById(R.id.txtEmailLogin)
        txtPassword=findViewById(R.id.txtPassLogin)
        btnLogin=findViewById(R.id.btn_login)
    }
    private fun inicializarEventos(){
        btnLogin.setOnClickListener {
            if (validar()) {
                //enviar a validar el usuario y contraseña en la api
                loginApi()
            }
        }
        //otro boton para al registro usuario
        
    }
    private fun validar():Boolean{
        var valido = true
        if (txtCorreo.text.toString().isBlank()){
            valido=false
            txtCorreo.error="Debe ingresar su correo"
        }
        if (txtPassword.text.toString().isBlank()){
            valido=false
            txtPassword.error="Debe ingresar su contraseña"
        }
        if(!valido){
            Toast.makeText(this,"Error en los datos por favor verificar",Toast.LENGTH_LONG).show()
        }

        return valido
    }
    private fun loginApi(){
        val usuarioRepository = UsuarioRepository()
        val usuario=Usuario(0,"",txtCorreo.text.toString(),txtPassword.text.toString())
        CoroutineScope(Dispatchers.IO).launch {
            val respuesta = usuarioRepository.login(usuario)
            try {
                withContext(Dispatchers.Main){
                    if (respuesta.isSuccessful){
                        //100,200,300
                        val gson = GsonBuilder().setPrettyPrinting().create()
                        val prettyJson = gson.toJson(JsonParser.parseString(respuesta.body()?.string()))
                        val jsonObjeto = JSONObject(prettyJson)
                        val data = jsonObjeto.getJSONObject("data")
                        if(data.has("Error")){
                            mostrarMensaje(data.getString("Error"))
                        }else{
                            //ir home
                            irHome()
                        }
                        Log.i("Login",prettyJson)
                    }else{
                        //400,500
                        val gson = GsonBuilder().setPrettyPrinting().create()
                        val prettyJson = gson.toJson(JsonParser.parseString(respuesta.errorBody()?.string()))
                        val jsonObjeto = JSONObject(prettyJson)
                        val mensaje = jsonObjeto.getString("message")
                        mostrarMensaje(mensaje)
                        Log.i("respuesta error json",prettyJson)
                    }
                }
            }catch (error:Exception){
                Log.e("Error Login",error.message.toString())
            }
        }
    }

    private fun mostrarMensaje(msj:String){
        Toast.makeText(this,msj,Toast.LENGTH_LONG).show()

    }
    private fun irHome(){
        val intentHome = Intent(this,MainActivity::class.java)
        startActivity(intentHome)
        finish()
    }



}