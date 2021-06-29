package com.example.mobileclientalog.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.mobileclientalog.data.api.ServiceBuilder
import com.example.mobileclientalog.data.api.ServiceProvider
import com.example.mobileclientalog.data.model.LoginUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.mobileclientalog.data.model.SignInBody
import com.example.mobileclientalog.utils.sharedPrefFile
import com.google.gson.Gson

class LogInRepository {

    companion object {

        val api: ServiceProvider by lazy {
            ServiceBuilder.buildService(ServiceProvider::class.java)
        }

        fun login(
            context: Context,
            email: String,
            password: String
        ) {

            var signinbody = SignInBody(email, password)
            val authLocataireRequest = api.userLogin(signinbody)

            val sharedPref = context.getSharedPreferences(
                sharedPrefFile, Context.MODE_PRIVATE
            )

            authLocataireRequest.enqueue(object : Callback<LoginUser> {

                @SuppressLint("RestrictedApi")
                override fun onResponse(call: Call<LoginUser>, response: Response<LoginUser>) {
                    if (!response.isSuccessful()) {
                        val gson = Gson()
                        val message: LoginUser = gson.fromJson(
                            response.errorBody()!!.charStream(),
                            LoginUser::class.java
                        )
                        Toast.makeText(context, "Erreur dans le login", Toast.LENGTH_LONG).show()

                    } else {
                        val resp = response.body()

                        if (resp != null) {

                            with(sharedPref?.edit()) {
                                this?.putBoolean("connected", true)
                                this?.apply()
                            }
                        }

                        Toast.makeText(context, "Connexion établie", Toast.LENGTH_SHORT).show()
                        //val myIntent = Intent(context, HomeActivity::class.java)
                        //context.startActivity(myIntent)
                        /*get DATA example : (in other activities)
                        * val preferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
                        * val userID = preferences.getString("userID", "Default")
                        */

                    }
                }

                override fun onFailure(call: Call<LoginUser>, t: Throwable) {
                    Toast.makeText(context, "Erreur", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}