package br.com.felipepalm14.eventour.domain.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.felipepalm14.eventour.domain.database.model.Event

@Dao
interface EventDao {

    @Query("SELECT * FROM event")
    fun getAllEvents() : LiveData<List<Event>>

    @Query("SELECT * FROM event WHERE id = :id")
    fun getEvent(id: Int): LiveData<Event>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(events: List<Event>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: Event)


}