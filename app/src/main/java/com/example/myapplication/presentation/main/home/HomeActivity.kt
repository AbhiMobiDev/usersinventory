package com.example.myapplication.presentation.main.home

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityHomeBinding
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.utils.LocalDatabase
import com.example.myapplication.utils.User

class HomeActivity : AppCompatActivity() {

    var currentUser:String? = null
    lateinit var adapter: UsersAdapter

    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val extras = intent?.extras
        if(extras != null){
            currentUser = extras.getString("user_type")
        }

        Log.e("TAG", "onCreate: user $currentUser")

        binding.Logout.setOnClickListener {
            finish()
        }

        adapter = UsersAdapter(LocalDatabase.inventoryItems, this, object : OnClickedEvents{
            override fun onDelete(item: User) {

                val tempList = LocalDatabase.inventoryItems

                for(i in LocalDatabase.inventoryItems.indices){
                    if(LocalDatabase.inventoryItems[i].name == item.name){
                        tempList.removeAt(i)
                    }
                }

                adapter.updateList(tempList)

            }

            override fun onEdit(item: User, edited: User) {
                val tempList = LocalDatabase.inventoryItems

                for(i in LocalDatabase.inventoryItems.indices){
                    if(LocalDatabase.inventoryItems[i].name == item.name){
                        tempList[i] = edited
                    }
                }

                adapter.updateList(tempList)
            }

        }, currentUser)


        if(currentUser == "admin"){
            var itemCount = 0
            binding.addItem.setOnClickListener {
                LocalDatabase.inventoryItems.add(User("User $itemCount", 1))
                adapter.updateList(LocalDatabase.inventoryItems)
                itemCount++
            }

        }else{
            binding.addItem.isEnabled = false
        }

        binding.recyClerView.layoutManager = LinearLayoutManager(this)
        binding.recyClerView.adapter = adapter

    }



}