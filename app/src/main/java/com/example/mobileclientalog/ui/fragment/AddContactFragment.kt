package com.example.mobileclientalog.ui.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import com.example.mobileclientalog.R
import com.example.mobileclientalog.data.repositories.AddClientRepository
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_auth.Email
import kotlinx.android.synthetic.main.fragment_add_contact.*

class AddContactFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_contact, container, false)
    }

    @Suppress("DEPRECATION")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val nom = editTextTextPersonName.text.toString()
        val prenom = editTextTextPersonName2.text.toString()
        val email = editTextTextPersonName3.text.toString()
        val phoneNumber = editTextTextPersonName4.text.toString()
        val mobile = editTextTextPersonName5.text.toString()
        val adresse = editTextTextPersonName6.text.toString()
        val sexe = editTextTextPersonName7.text.toString()

        button3.setOnClickListener {
            val nom = editTextTextPersonName.text.toString()
            val prenom = editTextTextPersonName2.text.toString()
            val email = editTextTextPersonName3.text.toString()
            val phoneNumber = editTextTextPersonName4.text.toString()
            val mobile = editTextTextPersonName5.text.toString()
            val adresse = editTextTextPersonName6.text.toString()
            val sexe = editTextTextPersonName7.text.toString()
            var adding = AddClientRepository.Companion
            adding.add(requireContext(), sexe, nom, prenom, email, phoneNumber, mobile, adresse)
        }

        button4.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_addContactFragment_to_listeContacts)
        }
    }
}