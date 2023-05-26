package com.example.usermanagment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.usermanagment.MainActivity
import com.example.usermanagment.database.daos.guestDaos

@Database(entities = [Guest::class], version = 1, exportSchema = false)


abstract class guestDatabase: RoomDatabase() {

    abstract fun guestDao():guestDaos
    companion object{
        @Volatile
        private var  INSTANCE:guestDatabase?=null

        fun getDatabase(context: Context):guestDatabase{

            val tempInstance=INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){

                val instance= Room.databaseBuilder(
                    context.applicationContext,
                    guestDatabase::class.java,
                    "guest_database"
                ).build()
                INSTANCE=instance
                return  instance
            }

        }
    }



}