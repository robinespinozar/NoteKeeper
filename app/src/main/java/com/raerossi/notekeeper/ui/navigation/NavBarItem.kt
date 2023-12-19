package com.raerossi.notekeeper.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.raerossi.notekeeper.R

sealed class NavBarItem(
    val route: String,
    @DrawableRes val unselectedIcon: Int,
    @DrawableRes val selectedIcon: Int,
    @StringRes val labelRes: Int
) {
    object HomeScreen : NavBarItem(
        route = "home_screen",
        unselectedIcon = R.drawable.ic_home,
        selectedIcon = R.drawable.ic_home_filled,
        labelRes = R.string.home
    )
    object ScheduleScreen : NavBarItem(
        route = "schedule_screen",
        unselectedIcon = R.drawable.ic_schedule,
        selectedIcon = R.drawable.ic_schedule_filled,
        labelRes = R.string.schedule
    )
    object ProfileScreen : NavBarItem(
        route = "profile_screen",
        unselectedIcon = R.drawable.ic_profile,
        selectedIcon = R.drawable.ic_profile_filled,
        labelRes = R.string.profile
    )
}