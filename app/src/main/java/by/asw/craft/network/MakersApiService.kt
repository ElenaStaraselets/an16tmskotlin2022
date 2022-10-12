package by.asw.craft.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://asw.by/mobile"
enum class CommonCraftApiFilter(val value: String) {
    SHOW_NEW("new"),
    SHOW_ALL("all") }

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface GatalogRequestorApiService {
    @GET("customerbytype")
    suspend fun getProperties(@Query("filter") type: String, @Query("catalogue") catalogue:Int):
            List<CatalogItem>

    @GET("extofferinfo")
    suspend fun getExtInfo( @Query("offerID") offer:Int):CatalogItem
}

object CraftTransportApi {
    val retrofitService: GatalogRequestorApiService by lazy {
        retrofit.create(GatalogRequestorApiService::class.java)
    }

}