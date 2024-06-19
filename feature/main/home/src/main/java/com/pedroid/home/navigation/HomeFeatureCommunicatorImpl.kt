package com.pedroid.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.pedroid.communicator.HomeFeatureCommunicator
import com.pedroid.navigation.navigateWithArgs
import javax.inject.Inject

class HomeFeatureCommunicatorImpl @Inject constructor(private val navController: NavController): HomeFeatureCommunicator {

    override fun launchFeature(homeFeatureArgs: HomeFeatureCommunicator.HomeFeatureArgs) {
        navController.navigateWithArgs(
            route = HomeNavigationNode.ROUTE,
            navOptions = NavOptions.Builder().setPopUpTo(homeFeatureArgs.previousRoute, true).build()
        )
    }
}