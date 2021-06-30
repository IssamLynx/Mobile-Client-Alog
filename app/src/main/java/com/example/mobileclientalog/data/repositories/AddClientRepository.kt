package com.example.mobileclientalog.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import com.example.mobileclientalog.data.api.ServiceBuilder
import com.example.mobileclientalog.data.api.ServiceProvider
import com.example.mobileclientalog.data.model.AddContact
import com.example.mobileclientalog.data.model.ResponseAddContact
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddClientRepository {

    companion object {

        val api: ServiceProvider by lazy {
            ServiceBuilder.buildService(ServiceProvider::class.java)
        }

        fun add(
            context: Context,
            sexe: String, firstName : String,
            lastName : String, email : String,
            phoneNumber : String, mobile : String, adress : String
        ) {

            val addc = AddContact(true, sexe, firstName, lastName, email,
                phoneNumber, mobile, adress)
            val addContact = api.addUser(addc)

            addContact.enqueue(object : Callback<ResponseAddContact> {

                override fun onResponse(call: Call<ResponseAddContact>, response: Response<ResponseAddContact>) {
                    if (!response.isSuccessful()) {

                        Toast.makeText(context, "Pas d'ajout", Toast.LENGTH_LONG).show()

                    } else {
                        Toast.makeText(context, "Ajouté", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ResponseAddContact>, t: Throwable) {
                    Toast.makeText(context, "Erreur Serveur", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}