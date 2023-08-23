package com.jones.mob_15

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jones.mob_15.data.model.Person
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    val firstName: MutableStateFlow<String> = MutableStateFlow("")
    val lastName: MutableStateFlow<String> = MutableStateFlow("")
    val finish1: MutableStateFlow<String> = MutableStateFlow("Finish initial value")
    val finish2: MutableSharedFlow<String> = MutableSharedFlow()

//    init {
//        viewModelScope.launch {
//            delay(2000)
//            finish2.emit("Hello there")
//        }
//    }

    fun onCreate() {
        viewModelScope.launch {
//            delay(1000)
            finish2.emit("Hello there")
        }
    }

    fun submit() {
        val person = Person(firstName.value, lastName.value)

        finish1.value = person.toString()

        viewModelScope.launch {
            finish2.emit(person.toString())
        }
    }
}