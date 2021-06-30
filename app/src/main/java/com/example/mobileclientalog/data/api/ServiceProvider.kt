package com.example.mobileclientalog.data.api

import com.example.mobileclientalog.data.model.LoginUser
import com.example.mobileclientalog.data.model.SignInBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceProvider {

    @POST("api/auth/locataire")
    fun userLogin(
        @Body info: SignInBody
    ): Call<LoginUser>
}