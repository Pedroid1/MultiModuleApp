package com.pedroid.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions

@SuppressLint("RestrictedApi")
fun NavController.navigateWithArgs(route: String, args: Bundle? = null, navOptions: NavOptions? = null) {
    findDestination(route)?.id?.let { destinationId ->
        val navigationOptions = navOptions ?: NavOptions.Builder()
            .build()
        navigate(resId = destinationId, args = args, navOptions = navigationOptions)
    }
}