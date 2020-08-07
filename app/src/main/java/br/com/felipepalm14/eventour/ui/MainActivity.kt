package br.com.felipepalm14.eventour.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import br.com.felipepalm14.eventour.R
import br.com.felipepalm14.eventour.databinding.ActivityMainBinding
import br.com.felipepalm14.eventour.ui.base.BaseActivity
import br.com.felipepalm14.eventour.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun getViewModelClass(): Class<MainViewModel> =
        MainViewModel::class.java

    override fun layoutId(): Int = R.layout.activity_main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }
}