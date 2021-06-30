package com.example.mobileclientalog.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Contacts")
data class Contact(
    @PrimaryKey
    val idContact:Int,
    val status:Boolean,
    val sexe:String,
    val firstName:String,
    val lastName:String,
    val email:String,
    val phoneNumber:String,
    val mobile:String,
    val address:String) {
}