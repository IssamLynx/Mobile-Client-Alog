package com.example.mobileclientalog.utils

import android.provider.ContactsContract
import androidx.lifecycle.MutableLiveData
import com.example.mobileclientalog.data.model.Contact

val sharedPrefFile: String = "kotlinsharedpreference"
var contacts = MutableLiveData<List<Contact>>()

class UtilsExample {
}