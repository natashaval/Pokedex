package com.natashaval.pokedex

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView
import com.natashaval.pokedex.interfaces.IActivityView
import com.natashaval.pokedex.databinding.ActivityMainBinding
import com.natashaval.pokedex.model.NamedApiResource
import com.natashaval.pokedex.ui.item.ItemBottomSheet
import com.natashaval.pokedex.ui.type.TypeBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), IActivityView {

  private lateinit var appBarConfiguration: AppBarConfiguration
  private lateinit var binding: ActivityMainBinding

  private var typeBottomSheet: TypeBottomSheet? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    val toolbar: Toolbar = findViewById(R.id.toolbar)
    setSupportActionBar(toolbar)
    setSpeedDialFab()

    val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
    val navView: NavigationView = findViewById(R.id.nav_view)
    val navController = findNavController(R.id.nav_host_fragment)
    // Passing each menu ID as a set of Ids because each
    // menu should be considered as top level destinations.
    appBarConfiguration = AppBarConfiguration(
        setOf(R.id.nav_home, R.id.nav_pokemon, R.id.nav_nature, R.id.nav_item, R.id.nav_type), drawerLayout)
    setupActionBarWithNavController(navController, appBarConfiguration)
    navView.setupWithNavController(navController)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.main, menu)
    return true
  }

  override fun onSupportNavigateUp(): Boolean {
    val navController = findNavController(R.id.nav_host_fragment)
    return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    val navController = findNavController(R.id.nav_host_fragment)
    return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
  }

  private fun setSpeedDialFab() {
    with(binding.layoutMain.fab) {
      inflate(R.menu.menu_fab)
      setOnActionSelectedListener { actionItem ->
        when (actionItem?.id) {
          R.id.fab_search -> {
            showToast("Search clicked")
            binding.layoutMain.fab.close()
          }
          R.id.fab_web -> {
            showToast("Web clicked")
          }
        }
        true
      }
    }
  }

  private fun showToast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
  }

  override fun onBackPressed() {
    if (binding.layoutMain.fab.isOpen) {
      binding.layoutMain.fab.close()
    } else {
      super.onBackPressed()
    }
  }

  override fun openItemBottomSheet(itemMode: Int, namedApiResource: NamedApiResource?) {
    ItemBottomSheet.newInstance(itemMode, namedApiResource).show(supportFragmentManager,
        ItemBottomSheet.ITEM_BOTTOM_SHEET_TAG)
  }

  override fun openTypeBottomSheet() {
    if (null == typeBottomSheet) {
      typeBottomSheet = TypeBottomSheet.newInstance()
    }
    typeBottomSheet?.show(supportFragmentManager, TypeBottomSheet.TYPE_BOTTOM_SHEET_TAG)
  }
}