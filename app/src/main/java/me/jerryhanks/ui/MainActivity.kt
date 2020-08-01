package me.jerryhanks.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import me.jerryhanks.R
import me.jerryhanks.di.Analytics
import me.jerryhanks.nav.Navigable
import me.jerryhanks.nav.Navigator
import me.jerryhanks.todo.core.ui.BaseActivity
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity(), Navigable {
    @Inject
    lateinit var analyticsService: Analytics

//    private val mainVM by viewModels<MainActivityViewModel>()

    private lateinit var navigator: Navigator

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigator = Navigator(this, this)

//        Timber.d("$navigator")

        Timber.d("${analyticsService.analyse("MainActivity", this)}")

        // invoke network call
//        mainVM.getTodos()

        navigator.navigate()

        bottomNavigationView.setupWithNavController(navController)
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    companion object {
        var topspace = 0
    }
}