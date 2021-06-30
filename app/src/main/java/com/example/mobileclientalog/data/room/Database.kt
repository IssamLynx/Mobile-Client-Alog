package com.sil1.autolibdz_rental.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mobileclientalog.data.model.Contact
import com.example.mobileclientalog.data.model.Converter
import com.sil1.autolibdz_rental.data.model.ContactsDao

//cette classe représentre notre base de données
@Database(entities = arrayOf(Contact::class),version=1)
@TypeConverters(Converter::class)
abstract class Database: RoomDatabase() {
    abstract fun getContactsDao():ContactsDao


}