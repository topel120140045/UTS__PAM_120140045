package com.example.uts_pam_120140045.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import com.example.uts_pam_120140045.model.DataItemUser
import com.example.uts_pam_120140045.model.ResponseApiUser
import com.example.uts_pam_120140045.R
import com.example.uts_pam_120140045.data.remote.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var adapter : UserAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var userArrayList : ArrayList<DataItemUser>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recyclerview_user)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        dataInitialize()
        adapter = UserAdapter(userArrayList)
        recyclerView.adapter = adapter
        searchView = view.findViewById(R.id.search_action)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        param1 = null
        param2 = null
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = ArrayList<DataItemUser>()
            for (i in userArrayList) {
                val username = "${i.firstName} ${i.lastName}"
                if (username.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show()
            }
            else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun dataInitialize(){
        userArrayList = arrayListOf()
        val client = ApiConfig.getApiService().getListUsers("1")

        client.enqueue(object : Callback<ResponseApiUser> {
            override fun onResponse(call: Call<ResponseApiUser>, response: Response<ResponseApiUser>) {
                if (response.isSuccessful) {
                    val dataArray = response.body()?.data as List<DataItemUser>
                    for (data in dataArray) {
                        adapter.addUser(data)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseApiUser>, t: Throwable) {
                Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }

}