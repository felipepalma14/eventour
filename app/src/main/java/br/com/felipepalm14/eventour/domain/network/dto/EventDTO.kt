package br.com.felipepalm14.eventour.domain.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class EventDTO(
    @Expose @SerializedName("id")
    val id: Long,
    @Expose @SerializedName("title")
    val title: String,
    @Expose @SerializedName("description")
    val description: String,
    @Expose @SerializedName("cupons")
    val cupons: ArrayList<CupomDTO>,
    @Expose @SerializedName("date")
    val date: Long,
    @Expose @SerializedName("image")
    val image: String,
    @Expose @SerializedName("latitude")
    val latitude: Double,
    @Expose @SerializedName("longitude")
    val longitude: Double,
    //val people: List<People>
    @Expose @SerializedName("price")
    val price: Double

)
