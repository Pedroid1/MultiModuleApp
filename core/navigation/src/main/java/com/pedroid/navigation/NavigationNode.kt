package com.pedroid.navigation

import androidx.navigation.NavGraphBuilder

interface NavigationNode {
    fun addNode(navGraphBuilder: NavGraphBuilder)
}