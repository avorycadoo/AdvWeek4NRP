package com.example.advweek4nrp.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.advweek4nrp.R
import com.example.advweek4nrp.model.Student
import com.example.advweek4nrp.view.ButtonDetailUpdateListener
import com.example.advweek4nrp.view.MainActivity
import com.example.advweek4nrp.view.StudentDetailFragmentArgs
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class DetailViewModel(application: Application, savedStateHandle: SavedStateHandle): AndroidViewModel(application),
ButtonDetailUpdateListener{
    val studentLD = MutableLiveData<Student>()
    val updateCompleteLD = MutableLiveData<Boolean>()

    val TAG = "volleyTag"

    var id = StudentDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).studentId
    private var queue: RequestQueue? = null
    fun fetch() {
//        val student1 = Student("16055","Nonie","1998/03/28","5718444778",
//            "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
//        studentLD.value = student1
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php?id=${id}"

        val stringRequest = StringRequest(
            Request.Method.GET, url, {
                val sType = object : TypeToken<Student>() {}.type
                val result = Gson().fromJson<Student>(it, sType)
                val student1 = result as Student

                studentLD.value = student1

                Log.d("show_volley", it)
            },
            {
                Log.e("show_volley", it.toString())
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

//    fun onUpdateClick() {
//        studentLD.value?.let { student ->
//            Observable.timer(5, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe {
//                    MainActivity.showNotification(
//                        student.name.toString(),
//                        "A new notification created",
//                        R.drawable.baseline_person_add_24
//                    )
//                }
//        }
//    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }

    override fun onButtonDetailUpdate(v: View) {
        Observable.timer(5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                studentLD.value?.let { student ->
                    Log.d("Messages", "five seconds")
                    MainActivity.showNotification(
                        student.name.toString(),
                        "A new notification created",
                        R.drawable.baseline_person_add_24
                    )
                    updateCompleteLD.value = true // Indicate that the update is complete
                }
            }
    }
}

