package com.example.recordkeeper

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.recordkeeper.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var backPressedCallback: OnBackPressedCallback


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }



        setupNavController()
        setupActionBar()
        setupOnBackPressed()




    }

    private fun setupOnBackPressed() {
        backPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showDialog()
            }
        }
        onBackPressedDispatcher.addCallback(this, backPressedCallback)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            backPressedCallback.isEnabled = destination.id in setOf(
                R.id.runningFragment,
                R.id.cyclingFragment
            )
        }
    }

    private fun showDialog() {

        MaterialAlertDialogBuilder(this)
            .setTitle("Warning!")
            .setMessage("You are about to leave the app. Are you sure you want to exit?")
            .setPositiveButton("Yes") {dialog, which ->
                finish()
            }
            .setNegativeButton("No") {dialog, which ->
                dialog.dismiss()
            }
            .show()
    }


    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.frameContent.id) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNav.setupWithNavController(navController)

    }

    private fun setupActionBar() {

        setSupportActionBar(binding.toolbar)

        // Optional: Show title from destination label
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.runningFragment,
                R.id.cyclingFragment
            ) // these are your top-level destinations
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.resetRunning -> {
                true
            }
            R.id.resetCycling -> {
                true
            }
            R.id.resetAll -> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(binding.frameContent.id)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }




}