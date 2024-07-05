package com.ivantrykosh.app.spofidata.presentation

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ivantrykosh.app.spofidata.ui.theme.Green

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomBar: @Composable () -> Unit = {
        BottomNavigation(modifier = Modifier.wrapContentSize(), backgroundColor = Green, contentColor = Color.Black) {
            screensInBottom.forEach { bottomScreen ->
                val isSelected = currentRoute == bottomScreen.route
                val color = if (isSelected) Color.White else Color.Black

                BottomNavigationItem(
                    selected = isSelected,
                    onClick = { navController.navigate(bottomScreen.route) },
                    icon = { Icon(painter = painterResource(id = bottomScreen.icon), contentDescription = bottomScreen.title, tint = color) },
                    label = { Text(text = bottomScreen.title, color = color) },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.Black
                )
            }
        }
    }

    Scaffold(
        bottomBar = bottomBar,
        backgroundColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        NavGraph(navController = navController, paddingValues = paddingValues)
    }
}