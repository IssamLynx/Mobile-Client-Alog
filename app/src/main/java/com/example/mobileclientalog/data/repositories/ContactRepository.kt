package com.example.mobileclientalog.data.repositories

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mobileclientalog.data.api.ServiceBuilder
import com.example.mobileclientalog.data.api.ServiceProvider
import com.example.mobileclientalog.data.model.Contact
import com.example.mobileclientalog.data.room.RoomService
import com.example.mobileclientalog.data.room.RoomService.context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactRepository {
    companion object {
        val api: ServiceProvider by lazy {
            ServiceBuilder.buildService(ServiceProvider::class.java)
        }
        fun getContacts(TAG: String): MutableLiveData<List<Contact>> {
            if (checkNetwork()) {
                var call = api.getContacts() // consommation de l'api
                var contactRespond: List<Contact>?
                var contactList = mutableListOf<Contact>()
                var finalList = MutableLiveData<List<Contact>>()

                call.enqueue(object : Callback<List<Contact>> {
                    override fun onResponse(
                        call: Call<List<Contact>>,
                        response: Response<List<Contact>>
                    ) {
                        Log.i(TAG, "Display reservation List: call enqueue")

                        if (!response.isSuccessful) {
                            Log.i(TAG, "CODE:" + response.code().toString())
                            return
                        }

                        contactRespond = response.body()  // Getting the list
                        if (contactRespond != null) {
                            Log.i(TAG, "REPONSES: HERE is ALL THE reservations:")
                            for (m in contactRespond!!) {
                                var content = ""
                                content += " $m \n"
                                Log.i(TAG, "\n=========\n$content")
                                contactList.add(m)

                                RoomService.database.getContactsDao().addContact(m)
                            }
                            finalList.value = contactList
                            //Log.i("TAG",marqueList.toString())

//                            for (reservation in finalList.value!!) {
//                                Toast.makeText(context,reservation.dateReservation.toString()
//                                ,Toast.LENGTH_SHORT)
//                               // RoomService.database.getReservationDao().addReservation(reservation)
//                            }
                        }
                    }

                    override fun onFailure(call: Call<List<Contact>>, t: Throwable) {
                        Log.i(TAG, "error CODE:" + t.message)
                    }
                })

                return finalList
            } else {
                var finalList = MutableLiveData<List<Contact>>()
                finalList.value = RoomService.database.getContactsDao().selectContacts()
                return finalList
            }
        }

        private fun checkNetwork(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }
}}