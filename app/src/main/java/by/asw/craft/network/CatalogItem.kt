package by.asw.craft.network


import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class CatalogItem(
    val id: Int,
    val imageID:Int,
    val catalogtype: Int,
    @Json(name = "img_src") val imgSrcUrl: String,
    val type: String,
    val itemname: String,
    val displayServices: String,
    val description: String,
    val lat: Double,
    val lon: Double,
    val price: Double
): Parcelable {
    val itemprice
        get() ="%.${2}f".format(price)
}

