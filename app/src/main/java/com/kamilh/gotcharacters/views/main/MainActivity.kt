package com.kamilh.gotcharacters.views.main

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.kamilh.gotcharacters.R
import com.kamilh.gotcharacters.base.BaseActivity
import com.kamilh.gotcharacters.data.Alert
import com.kamilh.gotcharacters.di.AppEventBus
import com.kamilh.gotcharacters.extensions.observeNotNull
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var appEventBus: AppEventBus
    private lateinit var viewModel: MainViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        navController = findNavController(this, R.id.fragment)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        NavigationUI.setupActionBarWithNavController(this, navController)

        initObservables()
    }

    private fun initObservables() {
        observeNotNull(appEventBus, viewModel::onAppEvent)
        observeNotNull(viewModel.alert, this::showDialog)
        observeNotNull(viewModel.direction, navController::navigate)
        observeNotNull(viewModel.shouldPop) { navController.popBackStack() }
    }

    private fun dialog(alert: Alert): AlertDialog {
        val builder = AlertDialog.Builder(this)
            .setTitle(alert.title)
            .setMessage(alert.message)
            .setCancelable(alert.isCancelable)

        alert.positiveButton?.let {
            builder.setPositiveButton(it.title) { _, _ ->
                it.callback()
            }
        }

        alert.negativeButton?.let {
            builder.setNegativeButton(it.title) { _, _ ->
                it.callback()
            }
        }

        alert.cancelAction?.let { action ->
            builder.setOnCancelListener { action() }
        }

        return builder.create()
    }

    private fun showDialog(alert: Alert) {
        dialog(alert).show()
    }

    override fun onSupportNavigateUp() = navController.navigateUp()
}
