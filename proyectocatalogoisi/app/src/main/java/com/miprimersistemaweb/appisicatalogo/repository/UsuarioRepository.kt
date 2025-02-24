package com.miprimersistemaweb.appisicatalogo.repository

import com.miprimersistemaweb.appisicatalogo.api.UsuarioService
import com.miprimersistemaweb.appisicatalogo.beans.Usuario
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit

class UsuarioRepository {

    suspend fun login(usuario: Usuario):Response<ResponseBody>{
        //10.0.2.2/tiendaLucila
        val retrofit = Retrofit.Builder().baseUrl("https://miprimersistemaweb.com").build()
        //Retrofit.Builder() → Crea una instancia de Retrofit, que es una librería usada para hacer
        // peticiones HTTP en Android/Kotlin.
        //.build() → Construye el objeto Retrofit.

        val service = retrofit.create(UsuarioService::class.java)
        //retrofit.create(UsuarioService::class.java) → Crea una instancia de UsuarioService,
        // que es la interfaz donde se definen las solicitudes HTTP.

        val bodyJson = JSONObject()
        bodyJson.put("email",usuario.email)
        bodyJson.put("password",usuario.password)
        val bodyjsonstring = bodyJson.toString()
        val requestBody:RequestBody = bodyjsonstring.toRequestBody("application/json".toMediaTypeOrNull())
        return service.login(requestBody)
    }

    suspend fun registro(usuario: Usuario):Response<ResponseBody>{
        //10.0.2.2/tiendaLucila
        val retrofit = Retrofit.Builder().baseUrl("https://miprimersistemaweb.com").build()
        //Retrofit.Builder() → Crea una instancia de Retrofit, que es una librería usada para hacer
        // peticiones HTTP en Android/Kotlin.
        //.build() → Construye el objeto Retrofit.

        val service = retrofit.create(UsuarioService::class.java)
        //retrofit.create(UsuarioService::class.java) → Crea una instancia de UsuarioService,
        // que es la interfaz donde se definen las solicitudes HTTP.

        val bodyJson = JSONObject()
        //nombre
        bodyJson.put("email",usuario.email)
        bodyJson.put("password",usuario.password)
        val bodyjsonstring = bodyJson.toString()
        val requestBody:RequestBody = bodyjsonstring.toRequestBody("application/json".toMediaTypeOrNull())
        return service.login(requestBody)
    }
}