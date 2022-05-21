package gg.solrudev.vitaland.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import gg.solrudev.vitaland.R
import gg.solrudev.vitaland.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

	private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind, R.id.container)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)
		val navController = binding.navHostFragmentActivityMain.getFragment<NavHostFragment>().navController
		val appBarConfiguration = AppBarConfiguration(
			setOf(
				R.id.navigation_login, R.id.navigation_home, R.id.navigation_profile, R.id.navigation_notifications
			)
		)
		navController.addOnDestinationChangedListener { _, destination, _ ->
			binding.navView.isVisible = destination.id != R.id.navigation_login
		}
		setupActionBarWithNavController(navController, appBarConfiguration)
		binding.navView.setupWithNavController(navController)
	}
}