package by.asw.craft.di

import android.content.Context
import by.asw.craft.network.CatalogItem
import by.asw.craft.network.CraftTransportApi
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule(private  var context: Context) {

    @Provides
    fun provideContext() = context

    @Provides
    suspend fun provideNetworkRepo(filter:String, catalogue:Int): List<CatalogItem> {
        return CraftTransportApi.retrofitService.getProperties(filter,catalogue)
    }
}
