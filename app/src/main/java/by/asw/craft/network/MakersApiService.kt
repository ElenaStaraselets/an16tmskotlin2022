package by.asw.craft.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"
enum class CommonCraftApiFilter(val value: String) {
    SHOW_RENT("rent"),
    SHOW_BUY("buy"),
    SHOW_ALL("all") }

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface GatalogRequestorApiService {
    @GET("realestate")
    suspend fun getProperties(@Query("filter") type: String, @Query("catalogue") catalogue:Int):
            List<CatalogItem>
}

object CraftTransportApi {
    val retrofitService: GatalogRequestorApiService by lazy {
        retrofit.create(GatalogRequestorApiService::class.java)
    }

}