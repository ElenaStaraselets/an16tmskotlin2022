package by.asw.craft.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*


@Parcelize
@Entity(tableName = "searchRequest")
data class SearchRequest(
    @PrimaryKey
    @ColumnInfo(name = "dateAdded")
    val dateAdded: Date,
    @ColumnInfo(name = "searchText")
    val noteText: String,
)
    : Parcelable {

}