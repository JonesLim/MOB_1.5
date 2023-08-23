package com.jones.mob_15

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        textView = findViewById(R.id.tvHelloThread)

//        run()

//        for (i in 1..10) {
//            Log.d("TestingThread", "Hello ${Thread.currentThread().name}, count: $i")
//            textView.text = "Hello ${Thread.currentThread().name}, count: $i"
//            Thread.sleep(5000)
//        delay(5000)
//        }

//        lifecycleScope.launch(Dispatchers.IO) {
//            withContext(Dispatchers.Main) {
//                textView.text = "Hello ${Thread.currentThread().name}"
//            }
//        }
    }

//    fun run() {
//        thread {
//            for (i in 1..10) {
//                Log.d("TestingThread", "Hello ${Thread.currentThread().name}, count: $i")
//                val name = Thread.currentThread().name
//                lifecycleScope.launch(Dispatchers.Main) {
//                    textView.text = "Hello $name, count: $i"
//                }
//                Thread.sleep(3000)
//            }
//        }
//    }
}