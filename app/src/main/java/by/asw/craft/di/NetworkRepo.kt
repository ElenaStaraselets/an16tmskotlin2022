package by.asw.craft.di

import android.util.Log

class NetworkRepo:Repository {
    override fun getRepository(): NetworkRepo
    {
        Log.d("DAGGER","NetworkRep")
        return this
    }

}
