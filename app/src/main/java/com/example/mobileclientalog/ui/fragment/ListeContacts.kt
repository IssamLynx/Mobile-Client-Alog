package com.example.mobileclientalog.ui.fragment

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobileclientalog.R
import com.example.mobileclientalog.data.model.Contact
import com.example.mobileclientalog.data.room.RoomService
import com.example.mobileclientalog.utils.contacts
import com.sil1.autolibdz_rental.ui.view.fragment.reservations.ContactViewModel
import kotlinx.android.synthetic.main.fragment_liste_contacts.*
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListeContacts.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListeContacts : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var adapter: ContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liste_contacts, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
       var adapter = ContactsAdapter(requireActivity())
        if(!checkNetwork() && RoomService.database.getContactsDao().selectContacts() == null)

        else
        {
        var viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        viewModel.getContacts()
        contacts.observe(requireActivity(), Observer {
                contact ->
            adapter.setContacts(contact)
        })

            recycleview.layoutManager = LinearLayoutManager(requireActivity())
            recycleview.adapter = adapter
    }}


}
private fun checkNetwork(): Boolean {
    val cm = RoomService.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
    return isConnected
}