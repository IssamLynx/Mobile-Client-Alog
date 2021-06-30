package com.sil1.autolibdz_rental.data.model

import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.mobileclientalog.data.model.Contact

//l'interface pour manipuler la table de docteur
@Dao
interface ContactsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addContact(contact: Contact)
    @Update
    fun updateContact(contact:Contact)
    @Delete
    fun deleteContact(contact:Contact)
    @Query("SELECT * FROM Contacts")
    fun selectContacts():List<Contact>

}



