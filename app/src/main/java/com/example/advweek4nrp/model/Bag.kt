package com.example.advweek4nrp.model

data class Bag(
    val id: String,
    val name: String,
    val items: List<String>,
    val details: Details,
    var images: String
)

data class Details(
    val color: String,
    val size: String
)