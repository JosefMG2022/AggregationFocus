package com.example.aggregationfocus.screen.home

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aggregationfocus.data.MongoDB
import com.example.aggregationfocus.model.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mongodb.kbson.ObjectId

class HomeViewModel : ViewModel() {
    var name = mutableStateOf("")
    var objectId = mutableStateOf("")

    var filtered = mutableStateOf(false)
    var data = mutableStateOf(emptyList<Person>())

    var myAge = mutableStateOf("".length)

    init {
        viewModelScope.launch {
            MongoDB.getData().collect {
                data.value = it
            }
        }
    }

    fun updateObjectId(id: String) {
        this.objectId.value = id
    }

    fun updateName(name: String) {
        this.name.value = name
    }

    fun updateAge(age: String) {
        this.myAge.value = age.toInt()
    }


    fun insertPerson() {
        viewModelScope.launch(Dispatchers.IO) {
            if (name.value.isNotEmpty() && myAge.value.toString().isNotEmpty() ) {

                MongoDB.insertPerson(person = Person().apply {
                    name = this@HomeViewModel.name.value
                    age = this@HomeViewModel.myAge.value

                })
            }
        }
    }

    fun updatePerson() {
        viewModelScope.launch(Dispatchers.IO) {
            if (objectId.value.isNotEmpty()) {
                MongoDB.updatePerson(person = Person().apply {
                    _id = ObjectId(hexString = this@HomeViewModel.objectId.value)
                    name = this@HomeViewModel.name.value
                    age = this@HomeViewModel.myAge.value


                })
            }
        }
    }

    fun deletePerson() {
        viewModelScope.launch {
            if (objectId.value.isNotEmpty()) {
                MongoDB.deletePerson(id = ObjectId(hexString = objectId.value))
            }
        }
    }

    fun filterData() {
        viewModelScope.launch(Dispatchers.IO) {
            if (filtered.value) {
                MongoDB.getData().collect {
                    filtered.value = false
                    name.value = ""
                    myAge.value = "".length // Could cause a crash
                    data.value = it
                }
            } else {
                MongoDB.filterData(name = name.value).collect {
                    filtered.value = true
                    data.value = it
                }
            }
        }
    }

}
