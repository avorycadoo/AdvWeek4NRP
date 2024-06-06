package com.example.advweek4nrp.view

import android.view.View
import com.example.advweek4nrp.model.Student

interface ButtonDetailClickListener {
    fun onButtonDetailClick(v: View)
}

interface ButtonDetailUpdateListener {
//    fun onButtonDetailUpdate(newStudentList: ArrayList<Student>)
        fun onButtonDetailUpdate(v: View)

}