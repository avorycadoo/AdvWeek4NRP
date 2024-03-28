package com.example.advweek4nrp.model

import com.google.gson.annotations.SerializedName

data class Student(
    val id:String?,
    @SerializedName("student_name")
    val name:String?,
    @SerializedName("birth_of_date")
    val bod:String?,
    val phone:String?,
    @SerializedName("photo_url")
    val photoUrl:String

//    var id:String?,
//    var name:String?,
//    var dob:String?,
//    var phone:String?,
//    var photoUrl:String?
)
