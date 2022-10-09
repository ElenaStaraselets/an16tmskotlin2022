package by.asw.craft

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class  DataModel(var text: String, var drawable: Int, var color: String, var catalogueType: Int): Parcelable {}


