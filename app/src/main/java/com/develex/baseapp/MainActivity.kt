package com.develex.baseapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.develex.baseapp.navigation.BottomNavigationBar
import com.example.compose.AppTheme

// Dataclass for keeping track if the app is in darkmode
data class DarkTheme(val isDark: Boolean = false)
val LocalTheme = compositionLocalOf { DarkTheme() }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val viewModel: MainViewModel = viewModel()

            val themeUserSetting by viewModel.themeUserSetting.collectAsState()

            val darkTheme = when (themeUserSetting) {
                MainViewModel.AppThemeEnum.MODE_AUTO -> DarkTheme(isSystemInDarkTheme())
                MainViewModel.AppThemeEnum.MODE_DAY -> DarkTheme(false)
                MainViewModel.AppThemeEnum.MODE_NIGHT -> DarkTheme(true)
                else -> { DarkTheme(isSystemInDarkTheme())}
            }

            CompositionLocalProvider (LocalTheme provides darkTheme) {
                AppTheme(darkTheme = LocalTheme.current.isDark) {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        BottomNavigationBar()
                    }
                }
            }
        }
    }
}