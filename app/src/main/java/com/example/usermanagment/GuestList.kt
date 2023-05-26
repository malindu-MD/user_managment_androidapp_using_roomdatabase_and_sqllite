package com.example.usermanagment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.usermanagment.adapters.GuestAdapter
import com.example.usermanagment.database.guestDatabase
import com.example.usermanagment.database.repositories.GuestRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GuestList : AppCompatActivity() {

    private lateinit var actionBtn:FloatingActionButton
    private  lateinit var repository:GuestRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_list)
        actionBtn=findViewById(R.id.floatingActionButton2)
        repository= GuestRepository(guestDatabase.getDatabase(this))
        val recyclerView: RecyclerView = findViewById(R.id.guestList)
        val adapter=GuestAdapter()
        recyclerView.adapter=adapter
        recyclerView.layoutManager=LinearLayoutManager(this)


        lifecycleScope.launch(Dispatchers.IO) {
            val allData = repository.readAllData // Assuming readAllData is a LiveData<List<Guest>>
            withContext(Dispatchers.Main) {
                allData.observe(this@GuestList) { guests ->
                    adapter.setData(guests)
                }
            }
        }




        actionBtn.setOnClickListener {

            intent = Intent(this, MainActivity::class.java)

            startActivity(intent)

        }

    }
}