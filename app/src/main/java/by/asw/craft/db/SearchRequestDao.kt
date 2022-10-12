package by.asw.craft.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchRequestDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addRecord(note: SearchRequest)

    @Query("SELECT * FROM searchRequest ORDER BY dateAdded DESC")
    fun getAllSearches(): Flow<List<SearchRequest>>

    @Delete
    suspend fun deleteRecord(note: SearchRequest)

}