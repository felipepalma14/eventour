package br.com.felipepalm14.eventour.domain.database

import androidx.room.TypeConverter
import br.com.felipepalm14.eventour.domain.database.model.Cupom
import br.com.felipepalm14.eventour.extensions.fromJson
import com.google.gson.Gson

class Converters {
    class CupomConverter {
        @TypeConverter
        fun toType(value: String): ArrayList<Cupom> {
            return Gson().fromJson<ArrayList<Cupom>>(value)
        }

        @TypeConverter
        fun toString(value: ArrayList<Cupom>): String {
            return Gson().toJson(value)
        }
    }
}
