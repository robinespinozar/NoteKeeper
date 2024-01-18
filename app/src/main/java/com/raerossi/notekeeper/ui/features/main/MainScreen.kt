package com.raerossi.notekeeper.ui.features.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.raerossi.notekeeper.ui.features.components.SetSystemColors
import com.raerossi.notekeeper.ui.features.components.navigationShadow
import com.raerossi.notekeeper.ui.navigation.MainNavHost
import com.raerossi.notekeeper.ui.navigation.NavBarItem
import com.raerossi.notekeeper.ui.theme.bottomBarContainer
import com.raerossi.notekeeper.ui.theme.primary30
import com.raerossi.notekeeper.ui.theme.primary50

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    SetSystemColors(colorStatusBar = Color(0xFFFFFFFF))

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController) { navItemRoute ->
                navController.navigate(navItemRoute) {
                    navController.graph.startDestinationRoute?.let { screenRoute ->
                        popUpTo(screenRoute) { saveState = true }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            MainNavHost(navController = navController)
        }
    }
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onNavItemClick: (String) -> Unit
) {
    val navItems = listOf(
        NavBarItem.HomeScreen,
        NavBarItem.ScheduleScreen,
        NavBarItem.ProfileScreen
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedRoute = navBackStackEntry?.destination?.route
    val isBottomBarDestination = navItems.any { it.route == selectedRoute }

    if (isBottomBarDestination) {
        BottomNavigation(
            modifier
                .height(74.dp)
                .background(color = MaterialTheme.colorScheme.background)
                .navigationShadow()
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
            backgroundColor = MaterialTheme.colorScheme.bottomBarContainer
        ) {
            navItems.forEach { item ->
                BottomNavigationItem(
                    selected = selectedRoute == item.route,
                    onClick = { onNavItemClick(item.route) },
                    icon = {
                        IconItem(
                            isSelected = selectedRoute == item.route,
                            selectedIcon = item.selectedIcon,
                            unselectedIcon = item.unselectedIcon
                        )
                    },
                    label = {
                        LabelItem(
                            isSelected = selectedRoute == item.route,
                            labelRes = item.labelRes
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun IconItem(
    isSelected: Boolean,
    @DrawableRes selectedIcon: Int,
    @DrawableRes unselectedIcon: Int
) {
    Icon(
        painter = if (isSelected) painterResource(id = selectedIcon) else painterResource(id = unselectedIcon),
        tint = if (isSelected) MaterialTheme.colorScheme.primary50 else Color(0xFF9CA2AA),
        contentDescription = "nav icon"
    )
    Spacer(modifier = Modifier.height(32.dp))
}

@Composable
private fun LabelItem(
    isSelected: Boolean,
    @StringRes labelRes: Int
) {
    Text(
        text = stringResource(id = labelRes),
        color = if (isSelected) MaterialTheme.colorScheme.primary30 else Color(0xFF6B7280),
        style = MaterialTheme.typography.labelSmall
    )
}