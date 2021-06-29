package com.example.mobileclientalog.ui.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileclientalog.R
import com.example.mobileclientalog.data.model.Contact

class ContactsAdapter (val context: Context): RecyclerView.Adapter<ContactsHolder>() {
    private var data = mutableListOf<Contact>()

    fun setContacts(contacts: List<Contact>) {
        data.clear()
        data.addAll(contacts)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsHolder {
        return ContactsHolder(LayoutInflater.from(context).inflate(R.layout.contactelement, parent, false))
    }

    override fun onBindViewHolder(holder: ContactsHolder, position: Int) {
        holder.firstName.text = data[position].firstName
        holder.lastName.text = data[position].lastName
        holder.email.text=data[position].email
        holder.phoneNumber.text=data[position].phoneNumber

    }

    override fun getItemCount(): Int {
    return data.size
    }
}

class ContactsHolder(view: View) : RecyclerView.ViewHolder(view) {

    val firstName = view.findViewById<TextView>(R.id.firstName)
    val lastName = view.findViewById<TextView>(R.id.lastName)
    val email = view.findViewById<TextView>(R.id.email)
    val phoneNumber = view.findViewById<TextView>(R.id.phoneNumber)


}

