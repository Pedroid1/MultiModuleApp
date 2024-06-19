package com.pedroid.communicator

import java.io.Serializable

interface HomeFeatureCommunicator {
    fun launchFeature(homeFeatureArgs: HomeFeatureArgs)

    companion object {
        const val homeFeatureNavKey = "homeFeatureNavKey"
    }

    data class HomeFeatureArgs(
        val previousRoute: String
    ): Serializable
}