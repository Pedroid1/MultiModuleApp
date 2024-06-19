package com.pedroid.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import androidx.navigation.navigation
import com.pedroid.navigation.NavigationNode
import com.pedroid.onboarding.fragments.SplashScreenFragment
import com.pedroid.onboarding.fragments.ViewPagerFragment
import javax.inject.Inject

class OnboardingNavigationNode @Inject constructor() : NavigationNode {

    override fun addNode(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.apply {
            navigation(startDestination = START_DESTINATION, route = ROUTE) {
                fragment<SplashScreenFragment>(START_DESTINATION)
                fragment<ViewPagerFragment>(VIEW_PAGER_DESTINATION)
            }
        }
    }

    companion object {
        const val ROUTE = "onboarding_route"
        const val START_DESTINATION = "splash_screen"
        const val VIEW_PAGER_DESTINATION = "view_pager_screen"
    }
}