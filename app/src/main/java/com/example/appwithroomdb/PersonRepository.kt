package com.example.appwithroomdb

class PersonRepository(private val dao: PersonDao) {
    val people = dao.getAll()
    suspend fun add(person: Person) = dao.Insert(person)
    suspend fun update(person: Person) = dao.Update(person)
    suspend fun delete(person: Person) = dao.Delete(person)
}