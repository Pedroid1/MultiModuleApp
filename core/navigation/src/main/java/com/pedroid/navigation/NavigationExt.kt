package com.pedroid.navigation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.pedroid.core.navigation.R

@SuppressLint("RestrictedApi")
fun NavController.navigateWithArgs(route: String, args: Bundle? = null, navOptions: NavOptions? = null) {
    findDestination(route)?.id?.let { destinationId ->
        val navigationOptions = navOptions ?: NavOptions.Builder()
            .build()
        navigate(resId = destinationId, args = args, navOptions = navigationOptions)
    }
}

@SuppressLint("RestrictedApi")
fun NavController.navigateWithAnimation(route: String, args: Bundle? = null, popUpTo: String? = null, inclusive: Boolean = false) {
    findDestination(route)?.id?.let { destinationId ->
        val navigationOptions = NavOptions.Builder().apply {
            setEnterAnim(R.anim.slide_right_to_left_enter)
            setExitAnim(R.anim.slide_right_to_left_out)
            setPopEnterAnim(R.anim.slide_left_to_right_enter)
            setPopExitAnim(R.anim.slide_left_to_right_out)
            popUpTo?.let {
                setPopUpTo(popUpTo, inclusive)
            }
        }.build()
        navigate(resId = destinationId, args = args, navOptions = navigationOptions)
    }
}