package com.frank.practicehilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.frank.practicehilt.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(null == savedInstanceState){
            supportFragmentManager.beginTransaction().add(R.id.container, HomeFragment())
                .addToBackStack("home".javaClass.name).commit()
        }
    }
}