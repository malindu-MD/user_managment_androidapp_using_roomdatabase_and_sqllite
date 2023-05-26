package com.example.usermanagment.database.repositories

import androidx.lifecycle.LiveData
import com.example.usermanagment.database.Guest
import com.example.usermanagment.database.daos.guestDaos
import com.example.usermanagment.database.guestDatabase

class GuestRepository(private val db:guestDatabase) {

    val readAllData: LiveData<List<Guest>> = db.guestDao().readAllData()
    suspend fun addGuest (guest:Guest){
       db.guestDao().addGuest(guest)
    }

    suspend fun delete(guest: Guest) = db.guestDao().delete(guest)


}