package com.develex.baseapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.develex.baseapp.screens.HomeScreen
import com.develex.baseapp.screens.Page2Screen
import com.develex.baseapp.screens.SettingsScreen

@Composable
fun BottomNavigationBar() {
    var navigationSelectedItem by remember {
        mutableStateOf(1)
    }

    val navController = rememberNavController()

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        bottomBar =  {
            NavigationBar {
                BottomNavigationItem().bottomNavigationItems().forEachIndexed{index, navigationItem  ->
                    NavigationBarItem(
                        selected = index == navigationSelectedItem,
                        label = {
                            Text(navigationItem.label)
                        },
                        icon = {
                            Icon(
                                navigationItem.icon,
                                contentDescription = navigationItem.label
                            )
                        },
                        onClick = {
                            navigationSelectedItem = index
                            navController.navigate(navigationItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }

            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Home.route,
            modifier = Modifier.padding(paddingValues = paddingValues)) {
            composable(Screens.Page2.route) {
                Page2Screen(navController)
            }
            composable(Screens.Home.route) {
                HomeScreen(navController)
            }
            composable(Screens.Settings.route) {
                SettingsScreen(navController)
            }
        }
    }
}