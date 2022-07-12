package com.example.hometask1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    var userList:MutableList<User>  = mutableListOf()
    var msg:String = "Добавление пользователя"
    val NOTIFY_KEY:String = "currentUser"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState != null ) {
            if( savedInstanceState.containsKey("userList")) {
                var arr: ArrayList<User> =
                    savedInstanceState?.getParcelableArrayList("userList")!!

                userList.addAll(arr);
            }
            if( savedInstanceState.containsKey(NOTIFY_KEY)) {
                msg = savedInstanceState.getString(NOTIFY_KEY, "Добавление пользователя")
            }
        }

        addedUser.text = msg
        /*
        if(savedInstanceState != null && savedInstanceState.containsKey("userList")) {
            var arr: ArrayList<User> =
                savedInstanceState?.getParcelableArrayList("userList")!!

            userList.addAll(arr);

        }

        addedUser.text = msg
        */
    }

    fun storeUser(view: View) {

        if(editPersonName.text.toString() == ""){
            Toast.makeText(this, "Заполните поле Name",Toast.LENGTH_SHORT).show()
            return
        }

        if(editPersonSecondName.text.toString() == ""){
            Toast.makeText(this, "Заполните поле Фамилия",Toast.LENGTH_SHORT).show()
            return
        }

        if(editAge.text.toString() == "" || Integer.parseInt(editAge.text.toString()) == 0){
            Toast.makeText(this, "Заполните поле возраст",Toast.LENGTH_SHORT).show()
            return
        }
        var usr = User()
        usr.name = editPersonName.text.toString()
        usr.secondname = editPersonSecondName.text.toString()
        usr.age = Integer.parseInt(editAge.text.toString())

        val listIterator = userList.listIterator()
        var userExist = false;
        while (listIterator.hasNext()) {
            val cu = listIterator.next()
            if(cu.equals(usr)){
                userExist = true;
                break;
            }
        }


        if(userExist){
            msg = "Данный пользователь уже добавлен"
        }
        else{
            userList.add(usr)
            msg = usr.toString()
            editPersonName.setText("")
            editPersonSecondName.setText("")
            editAge.setText("")
        }

        addedUser.setText(msg)
    }

    override fun onSaveInstanceState(outState: Bundle) {

        outState?.run {
            putString(NOTIFY_KEY, msg)
            putParcelableArrayList("userList", ArrayList<Parcelable>(userList))
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)

        if(savedInstanceState != null ) {
            if( savedInstanceState.containsKey("userList")) {
                var arr: ArrayList<User> =
                    savedInstanceState?.getParcelableArrayList("userList")!!

                userList.addAll(arr);
            }
            if( savedInstanceState.containsKey(NOTIFY_KEY)) {
                msg = savedInstanceState.getString(NOTIFY_KEY, "Добавление пользователя")
            }
        }
    }
}