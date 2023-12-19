package com.raerossi.notekeeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.raerossi.notekeeper.ui.features.splash.SplashViewModel
import com.raerossi.notekeeper.ui.navigation.NoteKeeperApp
import com.raerossi.notekeeper.ui.theme.NoteKeeperTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteKeeperTheme {
                NoteKeeperApp()
            }
        }
    }
}