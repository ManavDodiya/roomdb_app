package com.example.appwithroomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insert(person: Person)

    @Update
    suspend fun Update(person: Person)

    @Delete
    suspend fun Delete(person: Person)

    @Query("select * from person order by id asc")
    fun getAll(): Flow<List<Person>>
}