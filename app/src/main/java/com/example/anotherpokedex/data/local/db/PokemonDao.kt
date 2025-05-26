package com.example.anotherpokedex.data.local.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.anotherpokedex.data.local.entities.PokemonEntity

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemon ORDER BY nationalDexNumber ASC")
    fun getPagedPokemon(): PagingSource<Int, PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemon: List<PokemonEntity>) : Unit

    @Query("DELETE FROM pokemon")
    suspend fun clearAll()
}