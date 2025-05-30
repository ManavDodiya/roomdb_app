package com.example.appwithroomdb

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PersonViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PersonRepository
    val people: StateFlow<List<Person>>

    init {
        val db = AppDatabase.getDatabase(application)
        repository = PersonRepository(db.personDao())
        people = repository.people.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    }

    fun add(name: String) = viewModelScope.launch {
        repository.add(Person(name = name))
    }

    fun update(person: Person) = viewModelScope.launch {
        repository.update(person)
    }

    fun delete(person: Person) = viewModelScope.launch {
        repository.delete(person)
    }
}

