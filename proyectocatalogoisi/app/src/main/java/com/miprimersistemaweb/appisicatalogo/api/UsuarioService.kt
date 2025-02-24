package com.miprimersistemaweb.appisicatalogo.api

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UsuarioService {

    //login
    @POST("api/users/login")
    suspend fun login(@Body requestBody:RequestBody):Response<ResponseBody>

    //registro usuario
    @POST("api/users/create")
    suspend fun registroUsuario(@Body requestBody:RequestBody):Response<ResponseBody>

}