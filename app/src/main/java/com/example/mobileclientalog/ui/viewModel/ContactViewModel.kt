package com.sil1.autolibdz_rental.ui.view.fragment.reservations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobileclientalog.data.model.Contact
import com.example.mobileclientalog.data.repositories.ContactRepository
import com.example.mobileclientalog.utils.contacts

class ContactViewModel : ViewModel() {
    private val TAG = "TAG-reservation-View-Model"



    fun getContacts() {
        contacts = ContactRepository.getContacts(TAG)
    }



}