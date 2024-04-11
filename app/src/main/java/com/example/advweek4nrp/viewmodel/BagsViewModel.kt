package com.example.advweek4nrp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.advweek4nrp.model.Bag
import com.example.advweek4nrp.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BagsViewModel(application: Application): AndroidViewModel(application)
{
//    private val TAG = "BagsViewModel"
//    val bagLoadErrorLD = MutableLiveData<Boolean>()
//    val loadingLD = MutableLiveData<Boolean>()
    val bagLD = MutableLiveData<ArrayList<Bag>>()
    private var queue: RequestQueue? = null
    val TAG = "volleyTag"

//    val bagLoadError: LiveData<Boolean>
//        get() = bagLoadErrorLD
//    val loading: LiveData<Boolean>
//        get() = loadingLD
//    val Bags: LiveData<ArrayList<Student>>
//        get() = bagLD

    fun refresh() {
//        bagLoadErrorLD.value = false
//        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://10.0.0.2/bags/bags.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<List<Bag>>() { }.type
                val result = Gson().fromJson<List<Bag>>(it, sType)
                bagLD.value = result as ArrayList<Bag>?
                Log.d("showvoley", it.toString())
            },
            {
                Log.d("showvoley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

}