package br.com.felipepalm14.eventour.domain.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Event(
    @PrimaryKey var id: Long 	= 0L,
    @ColumnInfo var title: String = "",
    @ColumnInfo var description: String = "",
    @ColumnInfo var date: Long = 0L,
    @ColumnInfo var image: String = "",
    @ColumnInfo var latitude: Double = 0.0,
    @ColumnInfo var longitude: Double = 0.0,
    //val people: List<People>
    @ColumnInfo var price: Double = 0.0,
    var cupons: ArrayList<Cupom> = arrayListOf()

): Serializable
