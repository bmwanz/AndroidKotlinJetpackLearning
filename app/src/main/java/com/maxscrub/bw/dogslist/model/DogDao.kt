package com.maxscrub.bw.dogslist.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

// DAO = data access object
@Dao
interface DogDao {
    // functions to perform on the database

    // insert as many objects of type DogBreed as we like, will return list of UUIDs
    // suspend: done in a separate thread, cannot be accessed on local main thread
    @Insert
    suspend fun insertAll(vararg dogs: DogBreed) : List<Long>

    // SQL query, select everything from dogbreed entity, return as list of dogbreeds
    @Query("SELECT * FROM dogbreed")
    suspend fun getAllDogs() : List<DogBreed>

    // based on UUID we provide, get dog associated with it
    @Query("SELECT * FROM dogbreed WHERE uuid = :dogId")
    suspend fun getDog(dogId: Int): DogBreed

    // delete every entry from dogbreed table
    @Query("DELETE FROM dogbreed")
    suspend fun deleteAllDogs()

}