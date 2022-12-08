package com.toharifqi.sportagain

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.toharifqi.sportagain.ui.navigation.NavigationItem
import com.toharifqi.sportagain.ui.navigation.Screen
import com.toharifqi.sportagain.ui.screen.about.AboutScreen
import com.toharifqi.sportagain.ui.screen.detail.DetailScreen
import com.toharifqi.sportagain.ui.screen.favorite.FavoriteScreen
import com.toharifqi.sportagain.ui.screen.home.HomeScreen
import com.toharifqi.sportagain.ui.theme.SportAgainTheme

@Composable
fun SportAgainApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isInMainScreen = currentRoute == Screen.Home.route || currentRoute == Screen.Favorite.route

    Scaffold(
        bottomBar = {
            if (isInMainScreen) {
                BottomBar(navController)
            }
        },
        floatingActionButton = {
            if (isInMainScreen) {
                FloatingButton(
                    onClick = {
                        navController.popBackStack()
                        navController.navigate(Screen.About.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    })
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen()
            }
            composable(Screen.SportDetail.route) {
                DetailScreen()
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
        }

    }
}

@Composable
fun FloatingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick
    ) {

    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val navigationItems = listOf(
        NavigationItem(
            title = stringResource(R.string.menu_home),
            icon = Icons.Default.Home,
            screen = Screen.Home
        ),
        NavigationItem(
            title = stringResource(R.string.menu_favorite),
            icon = Icons.Default.Favorite,
            screen = Screen.Favorite
        )
    )
    BottomNavigation {
        navigationItems.map { item ->
            BottomNavigationItem(
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SportAgainAppPreview() {
    SportAgainTheme {
        SportAgainApp()
    }
}
