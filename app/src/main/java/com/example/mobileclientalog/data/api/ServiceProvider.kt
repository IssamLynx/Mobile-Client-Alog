package com.example.mobileclientalog.data.api

import com.example.mobileclientalog.data.model.Contact
import com.example.mobileclientalog.data.model.LoginUser
import com.example.mobileclientalog.data.model.SignInBody
import retrofit2.Call
import retrofit2.http.*

interface ServiceProvider {

    @POST("api/auth/locataire")
    fun userLogin(
        @Body info: SignInBody
    ): Call<LoginUser>

    @GET("/contacts/get-contacts")
    fun getContacts(): Call<List<Contact>>
}