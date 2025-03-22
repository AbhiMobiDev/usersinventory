package com.example.myapplication.presentation.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.presentation.main.home.HomeActivity

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            Log.e("TAG", "onCreate: ${binding.userType.text.toString()}")
            if(binding.userType.text.toString() != "" &&  binding.userType.text.toString() == "admin"){
                val intent = Intent(this, HomeActivity::class.java)
                val bundle = Bundle()
                bundle.putString("user_type", "admin")
                intent.putExtras(bundle)
                startActivity(intent)
            }else if(binding.userType.text.toString() == "user"){
                val intent = Intent(this, HomeActivity::class.java)
                val bundle = Bundle()
                bundle.putString("user_type", "user")
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }

    }
}