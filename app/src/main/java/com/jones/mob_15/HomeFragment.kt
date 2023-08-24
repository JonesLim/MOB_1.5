package com.jones.mob_15

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jones.mob_15.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlin.system.measureTimeMillis


class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    private val mutex = Mutex()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.onCreate()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.finish1.asLiveData().observe(viewLifecycleOwner) {
            Log.d("TestingFlow", "finish1: $it")
        }

        viewModel.finish2.asLiveData().observe(viewLifecycleOwner) {
            Log.d("TestingFlow", "finish2: $it")

        }

        val r = 10.0
//        Log.d("coroutine", "Area using formula: ${Math.PI * r * r}")

        val timeTook1 = measureTimeMillis {
            Math.PI * r * r
        }
//        Log.d("coroutine", "Duration for area using formula: $timeTook1 ms")
//        Log.d("coroutine", "Area using integration: ${areaOfCircle(0.0, r, r)}")
//
//        val timeTook = measureTimeMillis {
//            areaOfCircle(0.0, r, r)
//        }
//        Log.d("coroutine", "Duration for area using integration: $timeTook ms")

//        lifecycleScope.launch(Dispatchers.Default) {
//            Log.d("coroutine", "Area using integration: ${optimizedAreaOfCircle(r)}")
//
//            val timeTook = measureTimeMillis {
//                optimizedAreaOfCircle(r)
//            }
//            Log.d("coroutine", "Duration for area using integration: $timeTook ms")
//
//            val timmeTook1 = measureTimeMillis {
//                areaOfACircle(0.0, r, r)
//            }
//
//            Log.d("coroutine", "Duration for area using integration: $timmeTook1 ms")
//        }

//        runBlocking {
//
//        }

        lifecycleScope.launch {
            viewModel.counter(20).collect {
                Log.d("coroutine", "Counter: $it")
                binding.tvCount.text = it.toString()
            }
        }
    }

    var finalArea = 0.0

    suspend fun optimizedAreaOfCircle(r: Double): Double {

        val seg = r / 10

        var x = 0.0

        val jobs: MutableList<Job> = mutableListOf()

        repeat(10) {
            val x1 = x
            val x2 = x + seg
            val job = lifecycleScope.launch(Dispatchers.Default) {
//                Log.d("coroutine", "${x2}, $r $seg")
                areaOfACircle(x1, x2, r)
            }
            jobs.add(job)
//                Log.d("coroutine","out ${}")
            x = x + seg
        }
        jobs.forEach {
            it.join()
        }
        return finalArea * 4
    }

    suspend fun areaOfACircle(x1: Double, x2: Double, r: Double) {
//        Log.d("coroutine", "Range $x1 $x2")
        var area = 0.0
        val dx = 0.0001
        var x = x1 + dx

        while (x <= x2) {
            val smallArea = Math.sqrt(r * r - x * x) * dx
            area = area + smallArea
            x = x + dx
        }
//        mutex.withLock {

            finalArea = finalArea + area
//        }
    }
}