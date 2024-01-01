package com.raerossi.notekeeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.raerossi.notekeeper.ui.navigation.NoteKeeperApp
import com.raerossi.notekeeper.ui.theme.NoteKeeperTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteKeeperTheme {
                NoteKeeperApp()
                //SignUpScreen(){}
                //VerificationScreen{}
            }
        }
    }
}