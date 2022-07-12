package com.example.hometask1

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User : Parcelable {
    var name = ""
    var secondname = ""
    var age : Int = 0

    fun equals(other: User): Boolean {
        return name.equals(other.name) && secondname.equals(other.secondname) && age == other.age
    }

    override fun toString(): String {
        return name +" "+ secondname + " " + age
    }
}