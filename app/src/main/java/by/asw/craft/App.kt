package by.asw.craft

import android.app.Application
import android.content.Context
import by.asw.craft.di.AppComponent
import by.asw.craft.di.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent

    companion object {
        lateinit  var appContext: Context
    }


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
        App.appContext = applicationContext
    }

}
fun Context.getAppComponent(): AppComponent {
    return when (this){
        is App -> appComponent
        else -> (this.applicationContext as App).appComponent        }

}