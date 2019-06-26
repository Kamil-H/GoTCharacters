package com.kamilh.gotcharacters.views.main

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.kamilh.gotcharacters.R
import com.kamilh.gotcharacters.base.BaseActivity
import com.kamilh.gotcharacters.data.Alert
import com.kamilh.gotcharacters.di.AppEventBus
import com.kamilh.gotcharacters.extensions.observeNotNull
import com.kamilh.gotcharacters.util.navigation.FragmentOwner
import javax.inject.Inject

class MainActivity : BaseActivity(), FragmentOwner {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var appEventBus: AppEventBus
    @Inject
    lateinit var mainNavigationController: MainNavigationController

    private lateinit var viewModel: MainViewModel

    override val container = R.id.container
    override val fragmentManager: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        if (savedInstanceState == null) {
            viewModel.checkState()
        }

        initObservables()
    }

    private fun initObservables() {
        observeNotNull(appEventBus, viewModel::onAppEvent)
        observeNotNull(viewModel.alert, this::showDialog)
        observeNotNull(viewModel.mainNavigationEvent, mainNavigationController::handle)
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

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}
