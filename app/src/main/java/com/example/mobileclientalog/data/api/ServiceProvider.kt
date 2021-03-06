package com.example.mobileclientalog.data.api

import com.example.mobileclientalog.data.model.Contact
import com.example.mobileclientalog.data.model.AddContact
import com.example.mobileclientalog.data.model.LoginUser
import com.example.mobileclientalog.data.model.ResponseAddContact
import com.example.mobileclientalog.data.model.SignInBody
import retrofit2.Call
import retrofit2.http.*

interface ServiceProvider {

    @POST("v1/contacts/")
    fun userLogin(
        @Body info: SignInBody
    ): Call<LoginUser>

    @GET("v1/contacts/get-contacts")
    fun getContacts(): Call<List<Contact>>
    @POST("v1/contacts/add-contact")
    fun addUser(
        @Body info: AddContact
    ): Call<ResponseAddContact>
}