package by.asw.craft.db

import android.content.Context
import androidx.room.*

@Database(
    entities = [SearchRequest::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(SearchRequestConverters::class)
abstract class SearchRequestDatabase : RoomDatabase() {

    abstract fun searchRequestDao(): SearchRequestDao

    companion object {
        @Volatile
        private var INSTANCE: SearchRequestDatabase? = null

        fun getDatabase(context: Context?): SearchRequestDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (INSTANCE == null && context!=null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            // Return database.
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): SearchRequestDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                SearchRequestDatabase::class.java,
                "search_database"
            ).build()
        }
    }
}