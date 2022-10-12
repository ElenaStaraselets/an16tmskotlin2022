package by.asw.craft

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import by.asw.craft.main.MainFragment

class MainActivity : AppCompatActivity() {
    //private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        val navHostFragment = supportFragmentManager.findFragmentById(
//            R.id.container
//        ) as NavHostFragment
//        navController = navHostFragment.navController

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

    }

    override fun onSupportNavigateUp()
            = findNavController(R.id.container).navigateUp()
}