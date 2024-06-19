package com.pedroid.home.navigation

import androidx.navigation.NavController
import com.pedroid.communicator.HomeFeatureCommunicator
import com.pedroid.navigation.navigateWithAnimation
import javax.inject.Inject

class HomeFeatureCommunicatorImpl @Inject constructor(private val navController: NavController): HomeFeatureCommunicator {

    override fun launchFeature(homeFeatureArgs: HomeFeatureCommunicator.HomeFeatureArgs) {
        navController.navigateWithAnimation(
            route = HomeNavigationNode.ROUTE,
            popUpTo = homeFeatureArgs.previousRoute,
            inclusive = true
        )
    }
}